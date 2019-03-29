package pres.gogym.dfs.module.model;

import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

/* 
 * 
 * gen by gogym 2019-03-26
 */
@Table(name = "gogym_oss.oss_api_call_log")
public class OssApiCallLog {

	/*
	 * 主键
	 */
	@AssignID("uuid")
	private String id;
	/*
	 * 读写类型，1读 2写
	 */
	private Integer callType;
	/*
	 * 应用ID
	 */
	private String appId;
	/*
	 * 来源Ip
	 */
	private String fromIp;
	/*
	 * 访问路劲
	 */
	private String storePath;
	/*
	 * 访问时间
	 */
	private Date createTime;

	public OssApiCallLog() {
	}

	/**
	 * 主键
	 *
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 读写类型，1读 2写
	 *
	 * @return
	 */
	public Integer getCallType() {
		return callType;
	}

	/**
	 * 读写类型，1读 2写
	 *
	 * @param callType
	 */
	public void setCallType(Integer callType) {
		this.callType = callType;
	}

	/**
	 * 应用ID
	 *
	 * @return
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * 应用ID
	 *
	 * @param appId
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * 来源Ip
	 *
	 * @return
	 */
	public String getFromIp() {
		return fromIp;
	}

	/**
	 * 来源Ip
	 *
	 * @param fromIp
	 */
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}

	/**
	 * 访问路劲
	 *
	 * @return
	 */
	public String getStorePath() {
		return storePath;
	}

	/**
	 * 访问路劲
	 *
	 * @param storePath
	 */
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	/**
	 * 访问时间
	 *
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 访问时间
	 *
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
