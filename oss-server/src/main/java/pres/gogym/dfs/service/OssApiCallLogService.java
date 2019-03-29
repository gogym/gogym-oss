/*
 * 文件名：OssApiCallLogService.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月26日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.service;

import java.util.List;

import pres.gogym.dfs.module.model.OssApiCallLog;
import pres.gogym.dfs.module.vo.StatisticsApiVo;

public interface OssApiCallLogService {

	/**
	 * 
	 * Description: 添加一条记录
	 * 
	 * @param ossApiCallLog
	 * @return
	 * @see
	 */
	public String insertOne(OssApiCallLog ossApiCallLog);

	public List<StatisticsApiVo> statisticsApi(List<String> ids,String dateNow);

}
