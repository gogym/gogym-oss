/*
 * 文件名：DbConfig.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.configuration;

import org.beetl.sql.core.IDAutoGen;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import pres.gogym.utils.IDGenerator;

import com.ibeetl.starter.BeetlSqlMutipleSourceCustomize;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DbConfig {

	@Bean(name = "ossDatasource")
	public DataSource ossDatasource(Environment env) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.ossDatasource.url"));
		ds.setUsername(env
				.getProperty("spring.datasource.ossDatasource.username"));
		ds.setPassword(env
				.getProperty("spring.datasource.ossDatasource.password"));
		ds.setDriverClassName(env
				.getProperty("spring.datasource.ossDatasource.driverClassName"));
		return ds;
	}

	/**
	 * 
	 * Description: 设置多数据源主从库
	 * 
	 * @param manageDatasource
	 * @return
	 * @see
	 */
	@Bean(name = "ossSqlDataSource")
	public BeetlSqlDataSource ossSqlDataSource(
			@Qualifier("ossDatasource") DataSource ossDatasource) {
		BeetlSqlDataSource beetlSqlDataSource = new BeetlSqlDataSource();
		beetlSqlDataSource.setMasterSource(ossDatasource);
		return beetlSqlDataSource;
	}

	/**
	 * 
	 * Description: 配置多数据源判断
	 * 
	 * @param manageSqlDataSource
	 * @return
	 * @see
	 */
	@Bean
	public BeetlSqlMutipleSourceCustomize beetlSqlCustomize(
			@Qualifier("ossSqlDataSource") BeetlSqlDataSource ossSqlDataSource) {
		return new BeetlSqlMutipleSourceCustomize() {
			@Override
			public void customize(String dataSource, SQLManager sqlManager) {
				// 注册ID生成器，通过@AssignID("uuid")使用
				sqlManager.addIdAutonGen("uuid", new IDAutoGen<String>() {
					@Override
					public String nextID(String params) {
						return IDGenerator.getUUID();
					}
				});

				// 判断数据库源
				if (dataSource.equalsIgnoreCase("ossDatasource")) {
					sqlManager.setDs(ossSqlDataSource);
				}
			}
		};
	}

}
