package pres.gogym.dfs.module.model;

import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

/* 
 * 
 * gen by gogym 2019-03-08
 */
@Table(name = "gogym_oss.oss_app_info")
public class OssAppInfo {

	/*
	 * 主键
	 */
	@AssignID("uuid")
	private String id;
	/*
	 * 已用空间（bit）
	 */
	private Long useSpace;
	/*
	 * 应用ID
	 */
	private String appId;
	/*
	 * 应用密匙
	 */
	private String appSecret;
	/*
	 * 编码
	 */
	private String code;
	/*
	 * 开发者ID
	 */
	private String devId;
	private String intro;
	/*
	 * 名称
	 */
	private String name;
	/*
	 * 已用空间（带单位）
	 */
	private String useSpaceStr;

	/**
	 * 上传次数
	 */
	private Long funcWrite;

	/**
	 * 读取次数
	 */
	private Long funcRead;

	/*
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 图标
	 */
	private String ImgUrl;

	public OssAppInfo() {
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
	 * 已用空间（bit）
	 *
	 * @return
	 */
	public Long getUseSpace() {
		return useSpace;
	}

	/**
	 * 已用空间（bit）
	 *
	 * @param useSpace
	 */
	public void setUseSpace(Long useSpace) {
		this.useSpace = useSpace;
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
	 * 应用密匙
	 *
	 * @return
	 */
	public String getAppSecret() {
		return appSecret;
	}

	/**
	 * 应用密匙
	 *
	 * @param appSecret
	 */
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	/**
	 * 编码
	 *
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 编码
	 *
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 开发者ID
	 *
	 * @return
	 */
	public String getDevId() {
		return devId;
	}

	/**
	 * 开发者ID
	 *
	 * @param devId
	 */
	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * 名称
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 已用空间（带单位）
	 *
	 * @return
	 */
	public String getUseSpaceStr() {
		return useSpaceStr;
	}

	/**
	 * 已用空间（带单位）
	 *
	 * @param useSpaceStr
	 */
	public void setUseSpaceStr(String useSpaceStr) {
		this.useSpaceStr = useSpaceStr;
	}

	/**
	 * 创建时间
	 *
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 *
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getImgUrl() {

		return ImgUrl;
	}

	public void setImgUrl(String imgUrl) {

		ImgUrl = imgUrl;
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
