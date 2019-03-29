/*
 * 文件名：OssAppInfoServiceImpl.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月14日
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
import pres.gogym.dfs.module.model.OssApiCallLog;
import pres.gogym.dfs.module.model.OssAppInfo;
import pres.gogym.dfs.service.OssAppInfoService;
import pres.gogym.utils.FileUtil;

@Service
public class OssAppInfoServiceImpl implements OssAppInfoService {

	@Autowired
	private OssAppInfoDao ossAppInfoDao;

	@Autowired
	private OssApiCallLogDao ossApiCallLogDao;

	@Override
	public String insterOne(OssAppInfo ossAppInfo) {

		ossAppInfoDao.insertTemplate(ossAppInfo);
		return ossAppInfo.getId();

	}

	@Override
	public PageQuery<OssAppInfo> findAppList(OssAppInfo ossAppInfo, int start,
			int size) {

		PageQuery<OssAppInfo> query = new PageQuery<OssAppInfo>(start, size);
		ossAppInfoDao.templatePage(query);

		return query;
	}

	@Override
	public List<OssAppInfo> findAppListNoPage(OssAppInfo ossAppInfo) {
		return ossAppInfoDao.template(ossAppInfo);
	}

	@Override
	public Integer delById(String id) {
		return ossAppInfoDao.deleteById(id);
	}

	@Override
	public Integer updateOne(OssAppInfo ossAppInfo) {
		return ossAppInfoDao.updateTemplateById(ossAppInfo);
	}

	@Override
	public OssAppInfo findOne(OssAppInfo ossAppInfo) {
		return ossAppInfoDao.templateOne(ossAppInfo);
	}

	@Override
	public Integer updateFuncRead(OssAppInfo ossAppInfo, String fromIp,
			String path) {

		// 先通过排它锁锁住数据库记录
		OssAppInfo tmp = ossAppInfoDao.lockForAppId(ossAppInfo.getAppId());

		// 调用次数加1
		long funcNum = tmp.getFuncRead() + 1;
		tmp.setFuncRead(funcNum);

		OssApiCallLog ossApiCallLog = new OssApiCallLog();
		ossApiCallLog.setAppId(ossAppInfo.getAppId());
		ossApiCallLog.setFromIp(fromIp);
		ossApiCallLog.setStorePath(path);
		ossApiCallLog.setCallType(1);
		ossApiCallLog.setCreateTime(new Date());
		ossApiCallLogDao.insertTemplate(ossApiCallLog);

		return ossAppInfoDao.updateTemplateById(tmp);

	}

}
