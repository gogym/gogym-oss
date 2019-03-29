/*
 * 文件名：RedisConfig.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2018年10月29日 跟踪单号： 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.configuration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pres.gogym.dfs.configuration.properties.RedisProperties;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {

	// ---------------配置redis-----------------------

	/**
	 * 科普： 1、JedisCluster管理集群与ShardedJedisPool分片连接池实现分布式的区别
	 * ShardedJedisPool是redis没有集群功能之前客户端实现的一个数据分布式方案
	 * ，redis3.0提供集群之后，客户端则采用JedisCluster实现连接redis集群环境。
	 * ShardedJedisPool使用的是JedisShardInfo的instance的顺序或者name来做的一致性哈希
	 * ，JedisCluster使用的是CRC16算法来做的哈希槽。 redis
	 * cluster配置集群与使用Jedis的ShardedJedis做Redis集群的区别 2、集群环境，各个服务之间的数据是隔离的
	 * 无论是ShardedJedisPool的一致性哈希算法还是JedisCluster的CRC16哈希槽算法，都是把所有的服务叠加然后进行均匀的分割，
	 * 分割出来的每一个段或槽都是不重复的，所以导致存储的数据彼此之间也是处于隔离状态的。
	 * 3、jedis客户端操作redis主要三种模式：单台模式、分片模式（ShardedJedis）、集群模式（BinaryJedisCluster），
	 * 分片模式是一种轻量级集群
	 **/

	@Autowired
	private RedisProperties redisProperties;

	/**
	 * redis单机模式
	 * 
	 * @return
	 */
	@Bean
	public JedisPool getJedisPool() {

		JedisPool jedisPool = new JedisPool(genericObjectPoolConfig(),
				redisProperties.getIp(), redisProperties.getPort(),
				redisProperties.getTimeout(), redisProperties.getPassword());
		return jedisPool;
	}

	/**
	 * 初始化连接池
	 * 
	 * @return
	 */
	private GenericObjectPoolConfig genericObjectPoolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(redisProperties.getMaxTotal());
		poolConfig.setMaxIdle(redisProperties.getMaxIdle());
		poolConfig.setMinIdle(redisProperties.getMinIdle());
		poolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
		poolConfig.setTestOnBorrow(redisProperties.isTestOnBorrow());
		poolConfig.setTestOnReturn(redisProperties.isTestOnReturn());
		return poolConfig;
	}

}
