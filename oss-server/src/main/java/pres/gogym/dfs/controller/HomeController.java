/*
 * 文件名：HomeController.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pres.gogym.common.ResponseJson;
import pres.gogym.dfs.module.model.OssAppInfo;
import pres.gogym.dfs.module.vo.CountExtensionVo;
import pres.gogym.dfs.module.vo.HomeCountVo;
import pres.gogym.dfs.module.vo.HomeStatisticsVo;
import pres.gogym.dfs.module.vo.StatisticsApiVo;
import pres.gogym.dfs.service.OssApiCallLogService;
import pres.gogym.dfs.service.OssAppInfoService;
import pres.gogym.dfs.service.OssDeveloperService;
import pres.gogym.dfs.service.OssMaterialInfoService;

@RestController
@RequestMapping("/home/*")
public class HomeController {

	@Autowired
	private OssDeveloperService ossDeveloperService;

	@Autowired
	private OssMaterialInfoService ossMaterialInfoService;
	@Autowired
	private OssApiCallLogService ossApiCallLogService;

	@Autowired
	private OssAppInfoService ossAppInfoService;

	@PostMapping("countData")
	public ResponseJson countData(String devId) {

		if (StringUtils.isEmpty(devId)) {
			return ResponseJson.notParams();
		}

		HomeCountVo vo = ossDeveloperService.countData(devId);
		return ResponseJson.ok(vo);
	}

	/**
	 * 
	 * Description: 用量统计
	 * 
	 * @param startDate
	 * @return
	 * @see
	 */
	@PostMapping("findStatistics")
	public ResponseJson findStatistics(String devId, String startDate) {

		if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(devId)) {
			return ResponseJson.notParams();
		}

		OssAppInfo ossAppInfo = new OssAppInfo();
		ossAppInfo.setDevId(devId);
		List<OssAppInfo> appList = ossAppInfoService
				.findAppListNoPage(ossAppInfo);

		// 获取所有app的ID
		List<String> ids = new ArrayList<String>();
		for (OssAppInfo ossAppInfo2 : appList) {
			ids.add(ossAppInfo2.getAppId());
		}

		List<HomeStatisticsVo> list = ossMaterialInfoService.findStatistics(
				ids, startDate);
		return ResponseJson.ok(list);
	}

	@PostMapping("statisticsApi")
	public ResponseJson statisticsApi(String devId, String dateNow) {

		if (StringUtils.isEmpty(dateNow) || StringUtils.isEmpty(devId)) {
			return ResponseJson.notParams();
		}

		OssAppInfo ossAppInfo = new OssAppInfo();
		ossAppInfo.setDevId(devId);
		List<OssAppInfo> appList = ossAppInfoService
				.findAppListNoPage(ossAppInfo);

		// 获取所有app的ID
		List<String> ids = new ArrayList<String>();
		for (OssAppInfo ossAppInfo2 : appList) {
			ids.add(ossAppInfo2.getAppId());
		}

		List<StatisticsApiVo> list = ossApiCallLogService.statisticsApi(ids,
				dateNow);

		CountExtensionVo countExtensionVo = ossMaterialInfoService
				.countExtension(ids);

		Integer other = countExtensionVo.getTotal()
				- (countExtensionVo.getImg() + countExtensionVo.getVideo()
						+ countExtensionVo.getAudio() + countExtensionVo
							.getZip());
		countExtensionVo.setOther(other);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("countExtension", countExtensionVo);
		return ResponseJson.ok(result);
	}

}
