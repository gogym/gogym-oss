/*
 * 文件名：FastDFSTemplate.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月1日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.plugin.fastdfs.pool;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pres.gogym.dfs.plugin.fastdfs.factory.ConnectionPoolFactory;
import pres.gogym.dfs.plugin.fastdfs.factory.FastDFSTemplateFactory;

@Component
public class FastDFSTemplate {

	@Autowired
	private FastDFSTemplateFactory factory;

	private ConnectionPoolFactory connPoolFactory;

	public final String SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR = "/";

	public FastDFSTemplate(FastDFSTemplateFactory factory) {
		this.connPoolFactory = new ConnectionPoolFactory(factory);
		this.factory = factory;
	}

	/**
	 * 
	 * @Description: 上传文件
	 * @param data
	 * @param ext
	 *            后缀，如：jpg、bmp（注意不带.）
	 * @return
	 * @throws FastDFSException
	 * @author: Aaron
	 * @date: 2017年7月12日 下午7:01:32
	 */
	public FastDfsInfo upload(byte[] data, String ext) throws FastDFSException {
		return this.upload(data, ext, null);
	}

	/**
	 * 
	 * @Description: 上传文件
	 * @param data
	 * @param ext
	 *            后缀，如：jpg、bmp（注意不带.）
	 * @param values
	 * @return
	 * @throws FastDFSException
	 * @author: Aaron
	 * @date: 2017年7月12日 下午7:01:55
	 */
	public FastDfsInfo upload(byte[] data, String ext,
			Map<String, String> values) throws FastDFSException {
		NameValuePair[] valuePairs = null;
		if (values != null && !values.isEmpty()) {
			valuePairs = new NameValuePair[values.size()];
			int index = 0;
			for (Map.Entry<String, String> entry : values.entrySet()) {
				valuePairs[index] = new NameValuePair(entry.getKey(),
						entry.getValue());
				index++;
			}
		}
		StorageClient client = getClient();

		try {
			String[] uploadResults = client.upload_file(data, ext, valuePairs);
			String groupName = uploadResults[0];
			String remoteFileName = uploadResults[1];
			FastDfsInfo fastDfsInfo = new FastDfsInfo(groupName, remoteFileName);
			if (factory != null) {
				this.setFileAbsolutePath(fastDfsInfo);
			}
			return fastDfsInfo;
		} catch (Exception e) {
			throw new FastDFSException(e.getMessage(), e, 0);
		} finally {
			releaseClient(client);
		}
	}

	/**
	 * 
	 * @Description: 下载文件
	 * @param dfs
	 * @return
	 * @throws FastDFSException
	 * @author: Aaron
	 * @date: 2017年7月12日 下午7:02:27
	 */
	public byte[] loadFile(FastDfsInfo dfs) throws FastDFSException {
		return this.loadFile(dfs.getGroup(), dfs.getPath());
	}

	/**
	 * 
	 * @Description: 下载文件
	 * @param groupName
	 * @param remoteFileName
	 * @return
	 * @throws FastDFSException
	 * @author: Aaron
	 * @date: 2017年7月12日 下午7:02:46
	 */
	public byte[] loadFile(String remoteFileName) throws FastDFSException {
		StorageClient client = getClient();
		try {
			String[] result = split_file_id(remoteFileName);
			return client.download_file(result[0], result[1]);
		} catch (Exception e) {
			throw new FastDFSException(e.getMessage(), e, 0);
		} finally {
			releaseClient(client);
		}
	}

	/**
	 * 
	 * @Description: 下载文件
	 * @param groupName
	 * @param remoteFileName
	 * @return
	 * @throws FastDFSException
	 * @author: Aaron
	 * @date: 2017年7月12日 下午7:02:46
	 */
	public byte[] loadFile(String groupName, String remoteFileName)
			throws FastDFSException {
		StorageClient client = getClient();
		try {
			return client.download_file(groupName, remoteFileName);
		} catch (Exception e) {
			throw new FastDFSException(e.getMessage(), e, 0);
		} finally {
			releaseClient(client);
		}
	}

	/**
	 * 
	 * @Description: 删除文件
	 * @param dfs
	 * @throws FastDFSException
	 * @author: Aaron
	 * @date: 2017年7月12日 下午7:02:59
	 */
	public void deleteFile(FastDfsInfo dfs) throws FastDFSException {
		this.deleteFile(dfs.getGroup(), dfs.getPath());
	}

	/**
	 * 
	 * @Description: 删除文件
	 * @param groupName
	 * @param remoteFileName
	 * @throws FastDFSException
	 * @author: Aaron
	 * @date: 2017年7月12日 下午7:03:13
	 */
	public void deleteFile(String groupName, String remoteFileName)
			throws FastDFSException {
		int code;
		StorageClient client = getClient();
		try {
			code = client.delete_file(groupName, remoteFileName);
		} catch (Exception e) {
			throw new FastDFSException(e.getMessage(), e, 0);
		} finally {
			releaseClient(client);
		}
		if (code != 0) {
			throw new FastDFSException(code);
		}
	}

	/**
	 * 
	 * @Description: 设置远程可访问路径
	 * @param group
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws MyException
	 * @author: Aaron
	 * @date: 2017年7月12日 下午7:03:34
	 */
	public String setFileAbsolutePath(String group, String path)
			throws IOException, NoSuchAlgorithmException, MyException {
		int ts = (int) (System.currentTimeMillis() / 1000), port;
		String token = "";
		if (factory.isG_anti_steal_token()) {
			token = ProtoCommon.getToken(path, ts, factory.getG_secret_key());
			token = "?token=" + token + "&ts=" + ts;
		}
		List<String> addressList;
		if (factory.getNginx_address() != null) {
			addressList = factory.getNginx_address();
		} else {
			addressList = factory.getTracker_servers();
		}

		Random random = new Random();
		int i = random.nextInt(addressList.size());
		String[] split = addressList.get(i).split(":", 2);

		if (split.length > 1) {
			port = Integer.parseInt(split[1].trim());
		} else {
			port = factory.getG_tracker_http_port();
		}
		String address = split[0].trim();
		return factory.getProtocol() + address + ":" + port
				+ factory.getSepapator() + group + factory.getSepapator()
				+ path + token;

	}

	public void setFileAbsolutePath(FastDfsInfo fastDfsInfo)
			throws IOException, NoSuchAlgorithmException, MyException {
		fastDfsInfo.setFileAbsolutePath(this.setFileAbsolutePath(
				fastDfsInfo.getGroup(), fastDfsInfo.getPath()));
	}

	protected StorageClient getClient() {
		StorageClient client = null;
		while (client == null) {
			try {
				client = connPoolFactory.getClient();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	protected void releaseClient(StorageClient client) {
		connPoolFactory.releaseConnection(client);
	}

	// 切割文件名和组名
	public String[] split_file_id(String file_id) {

		String[] results = new String[2];
		int pos = file_id.indexOf(SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR);
		if ((pos <= 0) || (pos == file_id.length() - 1)) {
			return null;
		}
		results[0] = file_id.substring(0, pos); // group name
		results[1] = file_id.substring(pos + 1); // file name
		return results;
	}

}
