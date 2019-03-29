package pres.gogym.dfs.module.model;

import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.UpdateTime;

/* 
 * 
 * gen by gogym 2019-03-08
 */
@Table(name = "gogym_oss.oss_material_info")
public class OssMaterialInfo {

	/*
	 * 主键
	 */
	@AssignID("uuid")
	private String id;
	/*
	 * 最后修改时间
	 */
	private Date lastModifiedTime;
	/*
	 * 文件长度
	 */
	private Long len;
	/*
	 * 应用ID
	 */
	private String appId;
	/*
	 * 大小（有单位）
	 */
	private String byteStr;
	/*
	 * 拓展名
	 */
	private String extension;
	/*
	 * 来源IP
	 */
	private String fromIp;
	/*
	 * 原始文件名
	 */
	private String originalName;
	/*
	 * 相对路径
	 */
	private String storePath;
	
	@UpdateTime
	private Date createTime;

	public OssMaterialInfo() {
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
	 * 最后修改时间
	 *
	 * @return
	 */
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	/**
	 * 最后修改时间
	 *
	 * @param lastModifiedTime
	 */
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	/**
	 * 文件长度
	 *
	 * @return
	 */
	public Long getLen() {
		return len;
	}

	/**
	 * 文件长度
	 *
	 * @param len
	 */
	public void setLen(Long len) {
		this.len = len;
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
	 * 大小（有单位）
	 *
	 * @return
	 */
	public String getByteStr() {
		return byteStr;
	}

	/**
	 * 大小（有单位）
	 *
	 * @param byteStr
	 */
	public void setByteStr(String byteStr) {
		this.byteStr = byteStr;
	}

	/**
	 * 拓展名
	 *
	 * @return
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * 拓展名
	 *
	 * @param extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * 来源IP
	 *
	 * @return
	 */
	public String getFromIp() {
		return fromIp;
	}

	/**
	 * 来源IP
	 *
	 * @param fromIp
	 */
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}

	/**
	 * 原始文件名
	 *
	 * @return
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * 原始文件名
	 *
	 * @param originalName
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	/**
	 * 相对路径
	 *
	 * @return
	 */
	public String getStorePath() {
		return storePath;
	}

	/**
	 * 相对路径
	 *
	 * @param storePath
	 */
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
