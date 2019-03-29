/*
 * 文件名：AccessTokenInterceptor.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pres.gogym.common.ResponseJson;
import pres.gogym.dfs.annotation.AccessToken;
import pres.gogym.enums.Code;
import pres.gogym.plugin.redis.IRedisProxy;
import pres.gogym.utils.FastJsonUtils;

public class AccessTokenInterceptor implements HandlerInterceptor {

	@Autowired
	private IRedisProxy redisProxy;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		boolean flag = true;
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// 判断注解
			AccessToken accessToken = AnnotationUtils.findAnnotation(
					handlerMethod.getMethod(), AccessToken.class);
			if (accessToken != null) {
				// 获取请求头中的accessToken
				String header = request.getParameter("accessToken");

				// 缓存里是否存在accessToken
				if (redisProxy.get(header) == null) {
					return2Json(response);
					flag = false;
				}

			}
		}
		return flag;

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
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
