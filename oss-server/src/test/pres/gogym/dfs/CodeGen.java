/*
 * 文件名：CodeGen.java
 * 版权：Copyright by www.poly.com
 * 描述：代码生成器
 * 修改人：gogym
 * 修改时间：2019年3月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.MapperCodeGen;

public class CodeGen {

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.167.114:3306/gogym_oss?useUnicode=true&useSSL=false&allowMultiQueries=true&useAffectedRows=true";
		String userName = "root";
		String password = "123456";

		DBStyle style = new MySqlStyle();
		// 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
		ConnectionSource cs = ConnectionSourceHelper.getSimple(driver, url,
				userName, password);

		// 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
		UnderlinedNameConversion nc = new UnderlinedNameConversion();

		SQLLoader loader = new ClasspathLoader("/sql");

		SQLManager sqlManager = new SQLManager(style, loader, cs, nc,
				new Interceptor[] { new DebugInterceptor() });

		String path = "btl/pojo.btl";
		//ClassLoader classLoader = CodeGen.class.getClassLoader();
		//String classpath = classLoader.getResource(path).getPath();

		// 或者直接生成java文件
		GenConfig config = new GenConfig(path);
		// 指定模版文件在classpath 的路径
		config.preferBigDecimal(true);
		//config.setBaseClass("com.test.User");
		MapperCodeGen mapper = new MapperCodeGen("pres.gogym.dfs.module.dao");
		config.codeGens.add(mapper);
		
		try {
			// 快速生成，显示到控制台
			//sqlManager.genPojoCodeToConsole("oss_developer", config);
			// 生成查询，条件，更新sql模板，输出到控制台。
			// sqlManager.genSQLTemplateToConsole("oss_app_info");
			// 根据类来生成内置的增删改查sql语句，并打印到控制台
			// sqlManager.genBuiltInSqlToConsole(Class z);

			 sqlManager.genPojoCode("oss_api_call_log", "pres.gogym.dfs.module.model", config);
			 sqlManager.genSQLFile("oss_api_call_log", config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
