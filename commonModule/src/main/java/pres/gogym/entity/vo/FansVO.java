/*
 * 文件名：FansVO.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2018年12月28日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.entity.vo;

public class FansVO {

	private Integer userId;

	private Integer fansNum;// 粉丝数量

	private Integer attentNum;// 关注数量

	public Integer getUserId() {

		return userId;
	}

	public void setUserId(Integer userId) {

		this.userId = userId;
	}

	public Integer getFansNum() {

		return fansNum;
	}

	public void setFansNum(Integer fansNum) {

		this.fansNum = fansNum;
	}

	public Integer getAttentNum() {

		return attentNum;
	}

	public void setAttentNum(Integer attentNum) {

		this.attentNum = attentNum;
	}

}
