/*
 * 文件名：OssApiCallLogServiceImpl.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月26日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pres.gogym.dfs.module.dao.OssApiCallLogDao;
import pres.gogym.dfs.module.dao.OssAppInfoDao;
import pres.gogym.dfs.module.model.OssApiCallLog;
import pres.gogym.dfs.module.model.OssAppInfo;
import pres.gogym.dfs.module.vo.StatisticsApiVo;
import pres.gogym.dfs.service.OssApiCallLogService;
import pres.gogym.utils.FileUtil;

@Service
public class OssApiCallLogServiceImpl implements OssApiCallLogService {

	@Autowired
	private OssApiCallLogDao ossApiCallLogDao;

	@Override
	public String insertOne(OssApiCallLog ossApiCallLog) {

		ossApiCallLogDao.insertTemplate(ossApiCallLog);
		return ossApiCallLog.getId();

	}

	@Override
	public List<StatisticsApiVo> statisticsApi(List<String> ids, String dateNow) {

		return ossApiCallLogDao.statisticsApi(ids, dateNow);

	}

}
