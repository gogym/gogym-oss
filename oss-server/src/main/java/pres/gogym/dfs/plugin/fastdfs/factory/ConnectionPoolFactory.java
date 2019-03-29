/*
 * 文件名：ConnectionPoolFactory.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月1日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.plugin.fastdfs.factory;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.csource.fastdfs.StorageClient;

import pres.gogym.dfs.plugin.fastdfs.pool.PoolConfig;

public class ConnectionPoolFactory {
	private GenericObjectPool<StorageClient> pool;

	public ConnectionPoolFactory(FastDFSTemplateFactory fastDFSTemplateFactory) {
		pool = new GenericObjectPool<>(new ConnectionFactory(
				fastDFSTemplateFactory));
	}

	public StorageClient getClient() throws Exception {
		return pool.borrowObject();
	}

	public void releaseConnection(StorageClient client) {
		try {
			pool.returnObject(client);
		} catch (Exception ignored) {
		}
	}

	/**
	 * 
	 * @Description: 修改属性
	 * @param poolConfig
	 * @date: 2017年7月12日 下午6:59:02
	 */
	@SuppressWarnings("unused")
	private void toConfig(PoolConfig poolConfig) {
		pool.setMaxTotal(poolConfig.maxTotal);
		pool.setMaxIdle(poolConfig.maxIdle);
		pool.setMinIdle(poolConfig.minIdle);
		pool.setTestOnBorrow(poolConfig.testOnBorrow);
		pool.setMaxWaitMillis(poolConfig.maxWait);
	}

}
