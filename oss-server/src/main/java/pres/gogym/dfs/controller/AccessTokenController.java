/*
 * 文件名：AccessTokenController.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pres.gogym.common.Constants;
import pres.gogym.common.ResponseJson;
import pres.gogym.dfs.module.model.OssAppInfo;
import pres.gogym.dfs.service.OssAppInfoService;
import pres.gogym.plugin.redis.IRedisProxy;
import pres.gogym.utils.FastJsonUtils;
import pres.gogym.utils.LoggerUtils;
import pres.gogym.utils.TokenUtil;

@RestController
@RequestMapping("/accessToken/*")
public class AccessTokenController {

	@Autowired
	private OssAppInfoService ossAppInfoService;

	@Autowired
	private IRedisProxy redisProxy;

	@PostMapping("getAccessToken")
	public ResponseJson getAccessToken(String appId, String appSecret) {

		if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(appSecret)) {
			return ResponseJson.notParams();
		}

		OssAppInfo ossAppInfo = new OssAppInfo();
		ossAppInfo.setAppId(appId);
		ossAppInfo.setAppSecret(appSecret);

		OssAppInfo theOssAppInfo = ossAppInfoService.findOne(ossAppInfo);

		if (theOssAppInfo == null) {
			return ResponseJson.error("appId或appSecret错误！");
		}

		// 通过登录帐号生成唯一token
		String token = TokenUtil.TokenCreate(appId);

		try {
			String str = FastJsonUtils.toJSONString(theOssAppInfo);
			LoggerUtils.info(this.getClass(),str);

			// 把token添加到redis中,设置有效期为24小时
			redisProxy.setex(token, Constants.TOKEN_REDIS_TIME, str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回token给客户端
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("accessToken", token);
		return ResponseJson.ok(data);

	}
}
