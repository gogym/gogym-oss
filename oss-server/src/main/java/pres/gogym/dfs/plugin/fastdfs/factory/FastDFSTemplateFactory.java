/*
 * 文件名：FastDFSTemplateFactory.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月1日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.plugin.fastdfs.factory;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

import org.csource.common.IniFileReader;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerGroup;

import pres.gogym.dfs.plugin.fastdfs.pool.FastDFSException;
import pres.gogym.dfs.plugin.fastdfs.pool.PoolConfig;

public class FastDFSTemplateFactory {
	// 连接超时时间
	private int g_connect_timeout;
	// 网络超时时间
	private int g_network_timeout;
	// 编码
	private String g_charset;
	// tracker 端口
	private int g_tracker_http_port;
	// 防盗链token是否需要
	private boolean g_anti_steal_token;
	// FastDFS key
	private String g_secret_key;
	// tracker 地址
	private List<String> tracker_servers;
	// nginx 地址
	private List<String> nginx_address;

	private TrackerGroup g_tracker_group;

	// 连接池
	private PoolConfig poolConfig;
	int max_total = 100;
	int max_idle = 10;

	private String protocol = "http://";
	private String sepapator = "/";

	/**
	 * 
	 * Description: 配置初始化
	 * 
	 * @throws Exception
	 * @see
	 */
	public void init(String conf_filename) throws Exception {

		// 获取配置文件
		IniFileReader iniReader = new IniFileReader(conf_filename);

		g_connect_timeout = iniReader.getIntValue("connect_timeout",
				ClientGlobal.DEFAULT_CONNECT_TIMEOUT);
		if (g_connect_timeout <= 0) {
			g_connect_timeout = ClientGlobal.DEFAULT_CONNECT_TIMEOUT;
		}

		g_network_timeout = iniReader.getIntValue("network_timeout",
				ClientGlobal.DEFAULT_NETWORK_TIMEOUT);
		if (g_network_timeout <= 0) {
			g_network_timeout = ClientGlobal.DEFAULT_NETWORK_TIMEOUT;
		}

		// 转成毫秒
		g_connect_timeout *= 1000; // millisecond
		g_network_timeout *= 1000; // millisecond

		g_charset = iniReader.getStrValue("charset");
		if (g_charset == null || g_charset.length() == 0) {
			g_charset = "UTF-8";
		}

		g_tracker_http_port = iniReader.getIntValue("http.tracker_http_port",
				80);
		if (g_tracker_http_port <= 0) {
			g_tracker_http_port = 80;
		}

		String szTrackerServers = iniReader.getStrValue("tracker_server");
		if (szTrackerServers == null || szTrackerServers.isEmpty()) {
			throw new MyException("item \"tracker_server\" in " + conf_filename
					+ " not found");
		}
		tracker_servers = Arrays.asList(szTrackerServers.split(","));
		if (tracker_servers == null || tracker_servers.isEmpty()) {
			throw new FastDFSException("item \"tracker_server\"  not found", -1);
		}

		InetSocketAddress[] tracker_servers_socket = new InetSocketAddress[tracker_servers
				.size()];
		for (int i = 0; i < tracker_servers.size(); i++) {
			String str = tracker_servers.get(i);
			String[] parts = str.split("\\:", 2);
			if (parts.length != 2) {
				throw new FastDFSException(
						"the value of item \"tracker_server\" is invalid, the correct format is host:port",
						-2);
			}

			tracker_servers_socket[i] = new InetSocketAddress(parts[0].trim(),
					Integer.parseInt(parts[1].trim()));
		}
		// 设置服务器组
		g_tracker_group = new TrackerGroup(tracker_servers_socket);

		g_anti_steal_token = iniReader.getBoolValue("http.anti_steal_token",
				false);
		if (g_anti_steal_token) {
			if (g_secret_key == null || "".equals(g_secret_key)) {
				throw new FastDFSException("item \"secret_key\"  not found", -2);
			}
		}

		// 设置连接池配置
		max_total = iniReader.getIntValue("max_total", 100);
		max_idle = iniReader.getIntValue("max_idle", 100);

		setToGlobal();
	}

	/**
	 * 
	 * Description: 设置全局变量
	 * 
	 * @see
	 */
	private void setToGlobal() {
		ClientGlobal.setG_connect_timeout(this.g_connect_timeout);
		ClientGlobal.setG_network_timeout(this.g_network_timeout);
		ClientGlobal.setG_charset(this.g_charset);
		ClientGlobal.setG_tracker_http_port(this.g_tracker_http_port);
		ClientGlobal.setG_anti_steal_token(this.g_anti_steal_token);
		ClientGlobal.setG_secret_key(this.g_secret_key);
		ClientGlobal.setG_tracker_group(this.g_tracker_group);
	}

	// --------------get/set-------------------------

	public PoolConfig getPoolConfig() {
		if (poolConfig == null) {
			poolConfig = new PoolConfig();
			poolConfig.setMaxTotal(max_total);
			poolConfig.setMaxIdle(max_idle);
		}
		return poolConfig;
	}

	public void setPoolConfig(PoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public int getG_connect_timeout() {
		return g_connect_timeout;
	}

	public void setG_connect_timeout(int g_connect_timeout) {
		this.g_connect_timeout = g_connect_timeout;
	}

	public int getG_network_timeout() {
		return g_network_timeout;
	}

	public void setG_network_timeout(int g_network_timeout) {
		this.g_network_timeout = g_network_timeout;
	}

	public String getG_charset() {
		return g_charset;
	}

	public void setG_charset(String g_charset) {
		this.g_charset = g_charset;
	}

	public int getG_tracker_http_port() {
		return g_tracker_http_port;
	}

	public void setG_tracker_http_port(int g_tracker_http_port) {
		this.g_tracker_http_port = g_tracker_http_port;
	}

	public boolean isG_anti_steal_token() {
		return g_anti_steal_token;
	}

	public void setG_anti_steal_token(boolean g_anti_steal_token) {
		this.g_anti_steal_token = g_anti_steal_token;
	}

	public String getG_secret_key() {
		return g_secret_key;
	}

	public void setG_secret_key(String g_secret_key) {
		this.g_secret_key = g_secret_key;
	}

	public List<String> getTracker_servers() {
		return tracker_servers;
	}

	public void setTracker_servers(String tracker_servers) {
		this.tracker_servers = Arrays.asList(tracker_servers.split(","));
	}

	public List<String> getNginx_address() {
		return nginx_address;
	}

	public void setNginx_address(String nginx_address) {
		this.nginx_address = Arrays.asList(nginx_address.split(","));
	}

	public TrackerGroup getG_tracker_group() {
		return g_tracker_group;
	}

	public void setG_tracker_group(TrackerGroup g_tracker_group) {
		this.g_tracker_group = g_tracker_group;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSepapator() {
		return sepapator;
	}

	public void setSepapator(String sepapator) {
		this.sepapator = sepapator;
	}

}
