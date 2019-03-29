/*
 * 文件名：HomeCountVo.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.module.vo;

public class HomeCountVo {

	public Integer appCount;

	private Integer metaCount;

	private String len;

	private Long funcWrite;

	private Long funcRead;

	public Integer getAppCount() {

		return appCount;
	}

	public void setAppCount(Integer appCount) {

		this.appCount = appCount;
	}

	public Integer getMetaCount() {

		return metaCount;
	}

	public void setMetaCount(Integer metaCount) {

		this.metaCount = metaCount;
	}

	public String getLen() {

		return len;
	}

	public void setLen(String len) {

		this.len = len;
	}

	public Long getFuncWrite() {

		return funcWrite;
	}

	public void setFuncWrite(Long funcWrite) {

		this.funcWrite = funcWrite;
	}

	public Long getFuncRead() {

		return funcRead;
	}

	public void setFuncRead(Long funcRead) {

		this.funcRead = funcRead;
	}

}
