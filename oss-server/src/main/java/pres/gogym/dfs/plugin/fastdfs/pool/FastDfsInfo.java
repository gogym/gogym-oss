/*
 * 文件名：FastDfsInfo.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月1日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.plugin.fastdfs.pool;

public class FastDfsInfo implements java.io.Serializable {
	// serialVersionUID : TODO
	private static final long serialVersionUID = 4858945733404165431L;
	private String group;
	private String path;
	private String fileAbsolutePath;

	public FastDfsInfo(String group, String path) {
		this.group = group;
		this.path = path;
	}

	@Override
	public String toString() {
		return "FastDfsInfo{" + "group='" + group + '\'' + ", path='" + path
				+ '\'' + '}';
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileAbsolutePath() {
		return fileAbsolutePath;
	}

	public void setFileAbsolutePath(String fileAbsolutePath) {
		this.fileAbsolutePath = fileAbsolutePath;
	}

}
