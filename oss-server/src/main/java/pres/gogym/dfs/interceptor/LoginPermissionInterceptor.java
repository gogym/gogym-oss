package pres.gogym.dfs.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pres.gogym.common.Constants;
import pres.gogym.common.ResponseJson;
import pres.gogym.enums.Code;
import pres.gogym.plugin.redis.IRedisProxy;
import pres.gogym.utils.FastJsonUtils;
import pres.gogym.utils.LoggerUtils;

/**
 * token权限拦截器
 * 
 * @author kokJuis
 * @version 1.0
 * @date 2017-3-8
 */
public class LoginPermissionInterceptor implements HandlerInterceptor {

	@Autowired
	private IRedisProxy redisProxy;

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object object)
			throws Exception {

		LoggerUtils.info("[token权限验证]");

		// 设置返回对象编码
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("text/html;charset=utf-8");

		// 获取请求头里的TOKEN
		String token = httpServletRequest.getHeader("Token");

		// 如果token为空，直接返回
		if (token == null) {
			return2Json(httpServletResponse);
			return false;
		}

		// 先看缓存里是否存在token
		if (redisProxy.get(token) != null) {
			long ttl = redisProxy.ttl(token);
			if (ttl < (3 * 24 * 60 * 60)) {
				redisProxy.expire(token, Constants.TOKEN_VALID_TIME);
			}
			return true;
		} else {
			// 不存在直接返回失败
			return2Json(httpServletResponse);
			return false;

		}

	}

	// 拦截后返回的信息
	private void return2Json(HttpServletResponse httpServletResponse) {

		ResponseJson ResponseJson = new ResponseJson(
				Code.NO_PERMISSION.getCode(), Code.NO_PERMISSION.getMsg());
		String json = FastJsonUtils.toJSONString(ResponseJson);

		try {
			httpServletResponse.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
