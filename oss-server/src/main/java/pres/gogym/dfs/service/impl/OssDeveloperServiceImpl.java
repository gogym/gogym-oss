/*
 * 文件名：OssDeveloperServiceImpl.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.service.impl;

import java.util.List;

import org.beetl.sql.core.db.KeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pres.gogym.dfs.module.dao.OssDeveloperDao;
import pres.gogym.dfs.module.model.OssDeveloper;
import pres.gogym.dfs.module.vo.HomeCountVo;
import pres.gogym.dfs.service.OssDeveloperService;

@Service
public class OssDeveloperServiceImpl implements OssDeveloperService {

	@Autowired
	private OssDeveloperDao ossDeveloperDao;

	@Override
	public List<OssDeveloper> findAll(int start, int size) {
		return ossDeveloperDao.all(start, size);
	}

	@Override
	public OssDeveloper findOne(OssDeveloper ossDeveloper) {
		return ossDeveloperDao.templateOne(ossDeveloper);
	}

	@Override
	public OssDeveloper findByUsername(String username) {
		return ossDeveloperDao.findByUsername(username);

	}

	@Override
	public String insterOne(OssDeveloper ossDeveloper) {
		ossDeveloperDao.insertTemplate(ossDeveloper);
		return ossDeveloper.getId();
	}

	@Override
	public Integer updateOne(OssDeveloper ossDeveloper) {
		return ossDeveloperDao.updateTemplateById(ossDeveloper);
	}

	@Override
	public HomeCountVo countData(String devId) {
		return ossDeveloperDao.countData(devId);
	}

}
