package pres.gogym.dfs.module.dao;

import java.util.List;

import org.beetl.sql.core.mapper.BaseMapper;

import pres.gogym.dfs.module.model.OssMaterialInfo;
import pres.gogym.dfs.module.vo.CountExtensionVo;
import pres.gogym.dfs.module.vo.HomeStatisticsVo;

/*
 * 
 * gen by beetlsql mapper 2019-03-08
 */
public interface OssMaterialInfoDao extends BaseMapper<OssMaterialInfo> {

	/**
	 * 
	 * Description: 按月统计
	 * 
	 * @param startDate
	 * @return
	 * @see
	 */
	public List<HomeStatisticsVo> findStatistics(List<String> ids,
			String startDate, String endDate);

	/**
	 * 
	 * Description: 统计文件类型
	 * 
	 * @return
	 * @see
	 */
	public CountExtensionVo countExtension(List<String> ids);

}
