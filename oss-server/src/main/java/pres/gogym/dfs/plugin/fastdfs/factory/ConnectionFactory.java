/*
 * 文件名：ConnectionFactory.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月1日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.plugin.fastdfs.factory;

import java.io.Closeable;
import java.io.IOException;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class ConnectionFactory extends BasePooledObjectFactory<StorageClient> {
	private FastDFSTemplateFactory factory;

	public ConnectionFactory(FastDFSTemplateFactory templateFactory) {
		this.factory = templateFactory;
	}

	@Override
	public StorageClient create() throws Exception {
		TrackerClient trackerClient = new TrackerClient(
				factory.getG_tracker_group());
		TrackerServer trackerServer = trackerClient.getConnection();
		return new StorageClient(trackerServer, null);
	}

	@Override
	public PooledObject<StorageClient> wrap(StorageClient storageClient) {
		return new DefaultPooledObject<>(storageClient);
	}

	public PooledObject<StorageClient> makeObject() throws Exception {
		return wrap(create());
	}

	public void destroyObject(StorageClient obj) throws Exception {
		close(obj.getTrackerServer());
	}

	private void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException ignored) {
			}
		}
	}
}
