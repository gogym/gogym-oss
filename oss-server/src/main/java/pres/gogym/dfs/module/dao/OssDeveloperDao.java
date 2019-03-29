package pres.gogym.dfs.module.dao;

import org.beetl.sql.core.annotatoin.*;
import org.beetl.sql.core.mapper.BaseMapper;

import pres.gogym.dfs.module.model.*;
import pres.gogym.dfs.module.vo.HomeCountVo;

/*
 * 
 * gen by beetlsql mapper 2019-03-11
 */

@SqlResource("ossDeveloper")
public interface OssDeveloperDao extends BaseMapper<OssDeveloper> {

	/**
	 * 
	 * Description: 通过用户名查找
	 * 
	 * @param username
	 * @return
	 * @see
	 */
	public OssDeveloper findByUsername(String username);

	/**
	 * 
	 * Description: 统计首页的一些数据
	 * 
	 * @return
	 * @see
	 */
	@SqlStatement(type = SqlStatementType.SELECT)
	public HomeCountVo countData(String devId);

}
