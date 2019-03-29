package pres.gogym.dfs.module.model;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by gogym 2019-03-08
*/
@Table(name="gogym_oss.oss_sys_user")
public class OssSysUser   {
	
	/*
	主键
	*/
	@AssignID("uuid")
	private String id ;
	private String intro ;
	/*
	密码
	*/
	private String password ;
	/*
	用户名
	*/
	private String username ;
	private Date createTime ;
	
	public OssSysUser() {
	}
	
	/**
	* 主键
	*@return 
	*/
	public String getId(){
		return  id;
	}
	/**
	* 主键
	*@param  id
	*/
	public void setId(String id ){
		this.id = id;
	}
	
	public String getIntro(){
		return  intro;
	}
	public void setIntro(String intro ){
		this.intro = intro;
	}
	
	/**
	* 密码
	*@return 
	*/
	public String getPassword(){
		return  password;
	}
	/**
	* 密码
	*@param  password
	*/
	public void setPassword(String password ){
		this.password = password;
	}
	
	/**
	* 用户名
	*@return 
	*/
	public String getUsername(){
		return  username;
	}
	/**
	* 用户名
	*@param  username
	*/
	public void setUsername(String username ){
		this.username = username;
	}
	
	public Date getCreateTime(){
		return  createTime;
	}
	public void setCreateTime(Date createTime ){
		this.createTime = createTime;
	}
	

}
