/*
 * 文件名：OssMaterialInfoServiceImpl.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.service.impl;

import java.util.Date;
import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pres.gogym.dfs.module.dao.OssApiCallLogDao;
import pres.gogym.dfs.module.dao.OssAppInfoDao;
import pres.gogym.dfs.module.dao.OssMaterialInfoDao;
import pres.gogym.dfs.module.model.OssApiCallLog;
import pres.gogym.dfs.module.model.OssAppInfo;
import pres.gogym.dfs.module.model.OssMaterialInfo;
import pres.gogym.dfs.module.vo.CountExtensionVo;
import pres.gogym.dfs.module.vo.HomeStatisticsVo;
import pres.gogym.dfs.service.OssMaterialInfoService;
import pres.gogym.utils.DateTimeUtil;
import pres.gogym.utils.FileUtil;

@Service
public class OssMaterialInfoServiceImpl implements OssMaterialInfoService {

	@Autowired
	private OssMaterialInfoDao ossMaterialInfoDao;

	@Autowired
	private OssAppInfoDao ossAppInfoDao;

	@Autowired
	private OssApiCallLogDao ossApiCallLogDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String insertOne(OssMaterialInfo ossMaterialInfo) throws Exception {
		// 写入数据库
		ossMaterialInfoDao.insertTemplate(ossMaterialInfo);

		// 先通过排它锁锁住数据库记录
		OssAppInfo ossAppInfo = ossAppInfoDao.lockForAppId(ossMaterialInfo
				.getAppId());
		// 计算使用空间
		long oldSpace = ossAppInfo.getUseSpace();
		long newSpace = oldSpace + ossMaterialInfo.getLen();
		// 调用次数加1
		long funcNum = ossAppInfo.getFuncWrite() + 1;

		ossAppInfo.setUseSpace(newSpace);
		ossAppInfo.setUseSpaceStr(FileUtil.FormetFileSize(newSpace));
		ossAppInfo.setFuncWrite(funcNum);
		// 更新app空间数据
		ossAppInfoDao.updateTemplateById(ossAppInfo);

		// 写入一条调用日志
		OssApiCallLog ossApiCallLog = new OssApiCallLog();
		ossApiCallLog.setAppId(ossAppInfo.getAppId());
		ossApiCallLog.setFromIp(ossMaterialInfo.getFromIp());
		ossApiCallLog.setStorePath(ossMaterialInfo.getStorePath());
		ossApiCallLog.setCallType(2);
		ossApiCallLog.setCreateTime(new Date());
		ossApiCallLogDao.insertTemplate(ossApiCallLog);

		return ossMaterialInfo.getId();

	}

	@Override
	public List<HomeStatisticsVo> findStatistics(List<String> ids,
			String startDate) {

		String endDate = DateTimeUtil.getCurrentDate();
		return ossMaterialInfoDao.findStatistics(ids, startDate, endDate);
	}

	@Override
	public CountExtensionVo countExtension(List<String> ids) {

		return ossMaterialInfoDao.countExtension(ids);

	}

	@Override
	public PageQuery<OssMaterialInfo> findList(OssMaterialInfo ossMaterialInfo,
			int start, int size) {

		PageQuery<OssMaterialInfo> query = new PageQuery<OssMaterialInfo>(
				start, size, ossMaterialInfo);
		ossMaterialInfoDao.templatePage(query);
		return query;
	}

	@Override
	public Integer delOne(String id) {

		return ossMaterialInfoDao.deleteById(id);

	}

}
