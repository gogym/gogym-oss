package pres.gogym.dfs.module.dao;

import java.util.List;

import org.beetl.sql.core.mapper.BaseMapper;

import pres.gogym.dfs.module.model.OssApiCallLog;
import pres.gogym.dfs.module.vo.StatisticsApiVo;

/*
 * 
 * gen by beetlsql mapper 2019-03-26
 */
public interface OssApiCallLogDao extends BaseMapper<OssApiCallLog> {

	/**
	 * 
	 * Description: 统计调用趋势
	 * 
	 * @param dateNow
	 * @return
	 * @see
	 */
	public List<StatisticsApiVo> statisticsApi(List<String> ids,String dateNow);

}
