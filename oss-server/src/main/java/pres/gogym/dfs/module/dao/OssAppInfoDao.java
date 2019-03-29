package pres.gogym.dfs.module.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import pres.gogym.dfs.module.model.OssAppInfo;

/*
 * 
 * gen by beetlsql mapper 2019-03-08
 */
@SqlResource("ossAppInfo")
public interface OssAppInfoDao extends BaseMapper<OssAppInfo> {

	/**
	 * 
	 * Description: 实现一个悲观锁，用于查询使用空间
	 * 
	 * @param key
	 * @return 
	 * @see
	 */
	public OssAppInfo lockForAppId(Object appId);

}
