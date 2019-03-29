/*
 * 文件名：OssMaterialInfoService.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;

import pres.gogym.dfs.module.model.OssMaterialInfo;
import pres.gogym.dfs.module.vo.CountExtensionVo;
import pres.gogym.dfs.module.vo.HomeStatisticsVo;

public interface OssMaterialInfoService {

	/**
	 * 
	 * Description: 写入一条信息
	 * 
	 * @param ossMaterialInfo
	 * @return
	 * @see
	 */
	public String insertOne(OssMaterialInfo ossMaterialInfo) throws Exception;

	/**
	 * 
	 * Description: 删除一条记录
	 * 
	 * @param id
	 * @return
	 * @see
	 */
	public Integer delOne(String id);

	/**
	 * 
	 * Description: 统计
	 * 
	 * @param startDate
	 * @return
	 * @see
	 */
	public List<HomeStatisticsVo> findStatistics(List<String> ids,String startDate);

	/**
	 * 
	 * Description: 统计文件类型
	 * 
	 * @return
	 * @see
	 */
	public CountExtensionVo countExtension(List<String> ids);

	/**
	 * 
	 * Description: 获取文件列表
	 * 
	 * @param ossMaterialInfo
	 * @return
	 * @see
	 */
	public PageQuery<OssMaterialInfo> findList(OssMaterialInfo ossMaterialInfo,
			int start, int size);

}
