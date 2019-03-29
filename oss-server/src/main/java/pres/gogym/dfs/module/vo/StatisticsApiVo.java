/*
 * 文件名：StatisticsApiVo.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月26日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.module.vo;

public class StatisticsApiVo {

	private String clickDate;

	private String hours;

	private String read;

	private String write;

	public String getClickDate() {

		return clickDate;
	}

	public void setClickDate(String clickDate) {

		this.clickDate = clickDate;
	}

	public String getHours() {

		return hours;
	}

	public void setHours(String hours) {

		this.hours = hours;
	}

	public String getRead() {

		return read;
	}

	public void setRead(String read) {

		this.read = read;
	}

	public String getWrite() {

		return write;
	}

	public void setWrite(String write) {

		this.write = write;
	}

}
