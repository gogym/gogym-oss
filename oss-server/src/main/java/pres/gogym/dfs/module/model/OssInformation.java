package pres.gogym.dfs.module.model;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by gogym 2019-03-08
*/
@Table(name="gogym_oss.oss_information")
public class OssInformation   {
	
	private String id ;
	private String createTime ;
	private String invokingRoot ;
	private String modifiedTime ;
	private String nginxLogPath ;
	private String root ;
	
	public OssInformation() {
	}
	
	public String getId(){
		return  id;
	}
	public void setId(String id ){
		this.id = id;
	}
	
	public String getCreateTime(){
		return  createTime;
	}
	public void setCreateTime(String createTime ){
		this.createTime = createTime;
	}
	
	public String getInvokingRoot(){
		return  invokingRoot;
	}
	public void setInvokingRoot(String invokingRoot ){
		this.invokingRoot = invokingRoot;
	}
	
	public String getModifiedTime(){
		return  modifiedTime;
	}
	public void setModifiedTime(String modifiedTime ){
		this.modifiedTime = modifiedTime;
	}
	
	public String getNginxLogPath(){
		return  nginxLogPath;
	}
	public void setNginxLogPath(String nginxLogPath ){
		this.nginxLogPath = nginxLogPath;
	}
	
	public String getRoot(){
		return  root;
	}
	public void setRoot(String root ){
		this.root = root;
	}
	

}
