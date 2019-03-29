/*
 * 文件名：OssDeveloperController.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pres.gogym.common.Constants;
import pres.gogym.common.ResponseJson;
import pres.gogym.dfs.module.model.OssDeveloper;
import pres.gogym.dfs.service.OssDeveloperService;
import pres.gogym.enums.Code;
import pres.gogym.plugin.redis.IRedisProxy;
import pres.gogym.utils.TokenUtil;

@RestController
@RequestMapping("/ossDeveloper/*")
public class OssDeveloperController {

	@Autowired
	private OssDeveloperService ossDeveloperService;

	@Autowired
	private IRedisProxy redisProxy;

	/**
	 * 
	 * Description: 获取所有的开发者
	 * 
	 * @return
	 * @see
	 */
	@PostMapping("findAll")
	public ResponseJson findAll(Integer start, Integer size) {

		List<OssDeveloper> list = ossDeveloperService.findAll(start, size);
		return ResponseJson.ok(list);

	}

	/**
	 * 
	 * Description: 开发者登录
	 * 
	 * @param ossDeveloper
	 * @return
	 * @see
	 */
	@PostMapping("developerLogin")
	public ResponseJson developerLogin(OssDeveloper ossDeveloper) {

		if (StringUtils.isEmpty(ossDeveloper.getUsername())
				|| StringUtils.isEmpty(ossDeveloper.getPassword())) {
			return ResponseJson.notParams();
		}

		OssDeveloper developer = ossDeveloperService.findOne(ossDeveloper);

		if (developer == null) {
			return ResponseJson.error(Code.NO_USER.getCode(),
					Code.NO_USER.getMsg());
		}

		// 通过登录帐号生成唯一token
		String token = TokenUtil.TokenCreate(ossDeveloper.getId());

		try {
			// 把token添加到redis中,设置有效期为24小时
			redisProxy.setex(token, Constants.TOKEN_REDIS_TIME, token);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回token给客户端
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("token", token);
		data.put("user", developer);
		return ResponseJson.ok(data);

	}

	/**
	 * 
	 * Description: 开发者注册
	 * 
	 * @param ossDeveloper
	 * @return
	 * @see
	 */
	@PostMapping("developerRegister")
	public ResponseJson developerRegister(OssDeveloper ossDeveloper) {

		if (StringUtils.isEmpty(ossDeveloper.getUsername())
				|| StringUtils.isEmpty(ossDeveloper.getPassword())) {
			return ResponseJson.notParams();
		}

		OssDeveloper developer = ossDeveloperService
				.findByUsername(ossDeveloper.getUsername());

		if (developer != null) {
			return ResponseJson.error(Code.IS_USER.getCode(),
					Code.IS_USER.getMsg());
		}

		ossDeveloper.setCreateTime(new Date());

		String result = ossDeveloperService.insterOne(ossDeveloper);

		if (result != null) {
			return ResponseJson.ok();
		}
		return ResponseJson.error();
	}

	/**
	 * 
	 * Description: 更新开发者
	 * 
	 * @param ossDeveloper
	 * @return
	 * @see
	 */
	@PostMapping("updateOssDeveloper")
	public ResponseJson updateOssDeveloper(OssDeveloper ossDeveloper) {

		if (StringUtils.isEmpty(ossDeveloper.getId())) {
			return ResponseJson.notParams();
		}

		Integer flag = ossDeveloperService.updateOne(ossDeveloper);

		if (flag > 0) {
			return ResponseJson.ok();
		}
		return ResponseJson.error();

	}

}
