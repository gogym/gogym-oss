package pres.gogym.dfs.module.model;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

/* 
 * 
 * gen by gogym 2019-03-11
 */
@Table(name = "gogym_oss.oss_developer")
public class OssDeveloper {

	/*
	 * 主键
	 */
	@AssignID("uuid")
	private String id;
	/*
	 * 邮箱
	 */
	private String email;
	private String intro;
	/*
	 * 昵称
	 */
	private String nickname;

	private String imgUrl;

	/*
	 * 密码
	 */
	private String password;
	/*
	 * 状态
	 */
	private String status;
	/*
	 * 电话
	 */
	private String tel;
	/*
	 * 用户名
	 */
	private String username;
	/*
	 * 创建时间
	 */
	private Date createTime;

	public OssDeveloper() {
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
	 * 邮箱
	 *
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 邮箱
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * 昵称
	 *
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 昵称
	 *
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 密码
	 *
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 状态
	 *
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 状态
	 *
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 电话
	 *
	 * @return
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 电话
	 *
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 用户名
	 *
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 用户名
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
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

		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {

		this.imgUrl = imgUrl;
	}

}
