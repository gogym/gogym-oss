/*
 * 文件名：OssAppInfoController.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pres.gogym.common.ResponseJson;
import pres.gogym.dfs.module.model.OssAppInfo;
import pres.gogym.dfs.service.OssAppInfoService;
import pres.gogym.utils.IDGenerator;

@RestController
@RequestMapping("/ossAppInfo/*")
public class OssAppInfoController {

	@Autowired
	private OssAppInfoService ossAppInfoService;

	/**
	 * 
	 * Description: 添加一个应用
	 * 
	 * @param ossAppInfo
	 * @return
	 * @see
	 */
	@PostMapping("addOssApp")
	public ResponseJson addOssApp(OssAppInfo ossAppInfo) {

		if (StringUtils.isEmpty(ossAppInfo.getDevId())
				|| StringUtils.isEmpty(ossAppInfo.getName())) {
			return ResponseJson.notParams();
		}

		// 生成appId
		String appId = IDGenerator.getUUID();
		// 生成app密匙
		String appSecret = IDGenerator.getUUID();

		ossAppInfo.setAppId(appId);
		ossAppInfo.setAppSecret(appSecret);
		ossAppInfo.setCreateTime(new Date());

		String result = ossAppInfoService.insterOne(ossAppInfo);

		if (result != null) {
			return ResponseJson.ok();
		}

		return ResponseJson.error();
	}

	/**
	 * 
	 * Description: 根据开发者获取应用
	 * 
	 * @param devId
	 * @return
	 * @see
	 */
	@PostMapping("findAppByDev")
	public ResponseJson findAppByDev(String devId, Integer start, Integer size) {

		if (StringUtils.isEmpty(devId)) {
			return ResponseJson.notParams();
		}

		if (null == start) {
			start = 1;
		}
		if (null == size) {
			size = 10;
		}

		OssAppInfo ossAppInfo = new OssAppInfo();
		ossAppInfo.setDevId(devId);

		PageQuery<OssAppInfo> query = ossAppInfoService.findAppList(ossAppInfo,
				start, size);

		Map<Object, Object> res = new HashMap<Object, Object>();
		res.put("list", query.getList());
		res.put("total", query.getTotalRow());

		return ResponseJson.ok(res);
	}

	/**
	 * 
	 * Description: 根据主键删除一个应用
	 * 
	 * @param id
	 * @return
	 * @see
	 */
	@PostMapping("delById")
	public ResponseJson delById(String id) {

		if (StringUtils.isEmpty(id)) {
			return ResponseJson.notParams();
		}

		Integer flag = ossAppInfoService.delById(id);

		if (flag > 0) {
			return ResponseJson.ok();
		}
		return ResponseJson.error();

	}

	/**
	 * 
	 * Description: 更新一个应用
	 * 
	 * @param ossAppInfo
	 * @return
	 * @see
	 */
	@PostMapping("updateOssApp")
	public ResponseJson updateOssApp(OssAppInfo ossAppInfo) {

		if (StringUtils.isEmpty(ossAppInfo.getId())) {
			return ResponseJson.notParams();
		}

		Integer flag = ossAppInfoService.updateOne(ossAppInfo);

		if (flag > 0) {
			return ResponseJson.ok();
		}
		return ResponseJson.error();

	}

	/**
	 * 
	 * Description: 查找一个应用
	 * 
	 * @param ossAppInfo
	 * @return
	 * @see
	 */
	@PostMapping("findOssApp")
	public ResponseJson findOssApp(OssAppInfo ossAppInfo) {

		OssAppInfo info = ossAppInfoService.findOne(ossAppInfo);
		return ResponseJson.ok(info);
	}

	/**
	 * 
	 * Description: 更新AppSecret
	 * 
	 * @param appSecret
	 * @return
	 * @see
	 */
	@PostMapping("updateAppSecret")
	public ResponseJson updateAppSecret(String id) {

		if (StringUtils.isEmpty(id)) {
			return ResponseJson.notParams();
		}
		// 生成app密匙
		String appSecret = IDGenerator.getUUID();

		OssAppInfo ossAppInfo = new OssAppInfo();
		ossAppInfo.setId(id);
		ossAppInfo.setAppSecret(appSecret);

		Integer flag = ossAppInfoService.updateOne(ossAppInfo);

		if (flag > 0) {

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("appSecret", appSecret);
			return ResponseJson.ok(result);
		}
		return ResponseJson.error();

	}
}
