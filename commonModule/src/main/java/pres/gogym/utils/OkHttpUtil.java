/*
 * 文件名：OkHttpUtil.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2018年7月30日 跟踪单号： 修改单号： 修改内容：
 */

package pres.gogym.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.ToString;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pres.gogym.exception.GlobalException;

/**
 * okhttp请求类
 * 
 * @author gogym
 * @version 2018年8月2日
 * @see OkHttpUtil
 * @since
 */
public class OkHttpUtil {

	public final static String GET = "GET";

	public final static String POST = "POST";

	public final static String PUT = "PUT";

	public final static String DELETE = "DELETE";

	public final static String PATCH = "PATCH";

	private final static String UTF8 = "UTF-8";

	private final static String GBK = "GBK";

	private final static String DEFAULT_CHARSET = UTF8;

	private final static String DEFAULT_METHOD = GET;

	private final static String DEFAULT_MEDIA_TYPE = "application/json";

	private final static boolean DEFAULT_LOG = false;

	private final static OkHttpClient client = new OkHttpClient.Builder()
			.connectionPool(new ConnectionPool(20, 5, TimeUnit.MINUTES))
			.readTimeout(20, TimeUnit.SECONDS)
			.connectTimeout(20, TimeUnit.SECONDS).build();

	// 测试一波
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("appKey", "67719596");
		map.put("timestamp", "1545037889639");
		map.put("nonce", "1234");
		map.put("sign",
				"1D681135D82B5912A40651625E1323150B3FC02DBEDA7B7922836BFCAC81D83E");
		try {
			String s = execute(OkHttp.builder().url("http://www.baidu.com")
					.method(GET).requestLog(true).responseLog(true).build());
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 *            URL地址
	 * @return
	 */
	public static String get(String url) throws Exception {
		return execute(OkHttp.builder().url(url).build());
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 *            URL地址
	 * @return
	 */
	public static String get(String url, String charset) throws Exception {
		return execute(OkHttp.builder().url(url).responseCharset(charset)
				.build());
	}

	/**
	 * 带查询参数的GET查询
	 * 
	 * @param url
	 *            URL地址
	 * @param queryMap
	 *            查询参数
	 * @return
	 */
	public static String get(String url, Map<String, String> queryMap)
			throws Exception {
		return execute(OkHttp.builder().url(url).queryMap(queryMap).build());
	}

	/**
	 * 带查询参数的GET查询
	 * 
	 * @param url
	 *            URL地址
	 * @param queryMap
	 *            查询参数
	 * @return
	 */
	public static String get(String url, Map<String, String> queryMap,
			String charset) throws Exception {
		return execute(OkHttp.builder().url(url).queryMap(queryMap)
				.responseCharset(charset).build());
	}

	/**
	 * POST application/json 获取参数要用
	 * 
	 * StringBuffer sb = new StringBuffer(); InputStream is =
	 * request.getInputStream(); InputStreamReader isr = new
	 * InputStreamReader(is); BufferedReader br = new BufferedReader(isr);
	 * String s = ""; while ((s = br.readLine()) != null) { sb.append(s); }
	 * String str = sb.toString();
	 * 
	 * @param url
	 * @param obj
	 * @return
	 */
	public static String postJson(String url, Object obj) throws Exception {
		return execute(OkHttp.builder().url(url).method(POST)
				.data(JSON.toJSONString(obj)).mediaType("application/json")
				.build());
	}

	public static String postJson(String url, Object obj,
			Map<String, String> headerMap) throws Exception {
		return execute(OkHttp.builder().url(url).method(POST)
				.headerMap(headerMap).data(JSON.toJSONString(obj))
				.mediaType("application/json").build());
	}

	/**
	 * POST application/x-www-form-urlencoded
	 * 
	 * @param url
	 * @param formMap
	 * @return
	 */
	public static String postForm(String url, Map<String, String> formMap)
			throws Exception {
		String data = "";
		if (MapUtils.isNotEmpty(formMap)) {
			data = formMap
					.entrySet()
					.stream()
					.map(entry -> String.format("%s=%s", entry.getKey(),
							entry.getValue())).collect(Collectors.joining("&"));
		}
		return execute(OkHttp.builder().url(url).method(POST).data(data)
				.mediaType("application/x-www-form-urlencoded").build());
	}

	public static String post(String url, String data, String mediaType,
			String charset) throws Exception {
		return execute(OkHttp.builder().url(url).method(POST).data(data)
				.mediaType(mediaType).responseCharset(charset).build());
	}

	/**
	 * Description: 上传文件与参数
	 * 
	 * @param url
	 *            上传地址
	 * @param params
	 *            需要附带上传的参数，没有传null
	 * @param fileParamName
	 *            上传文件对应的参数名
	 * @param bytes
	 *            文件流
	 * @param fileName
	 *            上传文件名称
	 * @return
	 * @see
	 */
	public static String uploadFlie(String url, Map<String, String> params,
			String fileParamName, byte[] bytes, String fileName) {

		// 返回值
		String result = "";

		// 3.x版本post请求换成FormBody 封装键值对参数
		MultipartBody.Builder builder = new MultipartBody.Builder();
		if (params != null) {
			for (String key : params.keySet()) {
				builder.addFormDataPart(key, params.get(key));
			}
		}

		if (bytes != null) {
			String TYPE = "application/octet-stream";
			RequestBody fileBody = RequestBody.create(MediaType.parse(TYPE),
					bytes);

			MultipartBody multipartBody = builder.setType(MultipartBody.FORM)
					.addFormDataPart(fileParamName, fileName, fileBody).build();

			Request request = new Request.Builder().url(url)
					.post(multipartBody).build();

			try {
				Response response = client.newCall(request).execute();
				byte[] bytesRelust = response.body().bytes();
				result = new String(bytesRelust, "UTF-8");
				// 记录返回日志
				LoggerUtils.info(OkHttpUtil.class, result);

			} catch (IOException e) {
				e.printStackTrace();
				LoggerUtils.error(OkHttpUtil.class, e.getMessage(), e);
			}

		}

		return result;
	}

	/**
	 * Description: 上传文件，附带上传参数
	 * 
	 * @param url
	 *            上传文件地址
	 * @param params
	 *            需要附带上传的参数，没有传null
	 * @param fileParamName
	 *            上传文件对应的参数名
	 * @param file
	 *            文件本身
	 * @return
	 * @see
	 */
	public static String uploadFlie(String url, Map<String, String> params,
			String fileParamName, File file) {

		// 返回值
		String result = "";

		// 3.x版本post请求换成FormBody 封装键值对参数
		MultipartBody.Builder builder = new MultipartBody.Builder();
		if (params != null) {
			for (String key : params.keySet()) {
				builder.addFormDataPart(key, params.get(key));
			}
		}

		if (file != null) {
			String TYPE = "application/octet-stream";
			RequestBody fileBody = RequestBody.create(MediaType.parse(TYPE),
					file);

			MultipartBody multipartBody = builder.setType(MultipartBody.FORM)
					.addFormDataPart(fileParamName, file.getName(), fileBody)
					.build();

			Request request = new Request.Builder().url(url)
					.post(multipartBody).build();

			try {
				Response response = client.newCall(request).execute();
				byte[] bytesRelust = response.body().bytes();
				result = new String(bytesRelust, "UTF-8");
				// 记录返回日志
				LoggerUtils.info(OkHttpUtil.class, result);

			} catch (IOException e) {
				e.printStackTrace();
				LoggerUtils.error(OkHttpUtil.class, e.getMessage(), e);
			}

		}

		return result;
	}

	// --------------------------------------------------------------------------------

	/**
	 * 通用执行方法
	 */
	private static String execute(OkHttp okHttp) throws Exception {
		if (StringUtils.isEmpty(okHttp.requestCharset)) {
			okHttp.requestCharset = DEFAULT_CHARSET;
		}
		if (StringUtils.isEmpty(okHttp.responseCharset)) {
			okHttp.responseCharset = DEFAULT_CHARSET;
		}
		if (StringUtils.isEmpty(okHttp.method)) {
			okHttp.method = DEFAULT_METHOD;
		}
		if (StringUtils.isEmpty(okHttp.mediaType)) {
			okHttp.mediaType = DEFAULT_MEDIA_TYPE;
		}
		if (okHttp.requestLog) {// 记录请求日志
			LoggerUtils.info(OkHttpUtil.class, okHttp.toString());
		}

		// 获取请求URL
		String url = okHttp.url;
		// 创建请求
		Request.Builder builder = new Request.Builder();

		if (MapUtils.isNotEmpty(okHttp.queryMap)) {
			String queryParams = okHttp.queryMap
					.entrySet()
					.stream()
					.map(entry -> String.format("%s=%s", entry.getKey(),
							entry.getValue())).collect(Collectors.joining("&"));
			url = String.format("%s%s%s", url, url.contains("?") ? "&" : "?",
					queryParams);
		}
		builder.url(url);

		// 设置请求头
		if (MapUtils.isNotEmpty(okHttp.headerMap)) {
			okHttp.headerMap.forEach(builder::addHeader);
		}

		// 设置请求类型
		String method = okHttp.method.toUpperCase();
		String mediaType = String.format("%s;charset=%s", okHttp.mediaType,
				okHttp.requestCharset);

		if (method.equals(GET)) {
			builder.get();
		} else if (ArrayUtils.contains(
				new String[] { POST, PUT, DELETE, PATCH }, method)) {
			RequestBody requestBody = RequestBody.create(
					MediaType.parse(mediaType), okHttp.data);
			builder.method(method, requestBody);
		} else {
			throw new GlobalException("未设置请求method");
		}

		// 返回值
		String result = "";
		try {
			Response response = client.newCall(builder.build()).execute();
			byte[] bytes = response.body().bytes();
			result = new String(bytes, okHttp.responseCharset);
			if (okHttp.responseLog) {// 记录返回日志
				LoggerUtils.info(OkHttpUtil.class, result);
			}
		} catch (Exception e) {
			LoggerUtils.error(OkHttpUtil.class, e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 一个内部类
	 * 
	 * @author gogym
	 * @version 2018年7月30日
	 * @see OkHttp
	 * @since
	 */

	@Builder
	@ToString(exclude = { "requestCharset", "responseCharset", "requestLog",
			"responseLog" })
	static class OkHttp {
		private String url;

		private String method = DEFAULT_METHOD;

		private String data;

		private String mediaType = DEFAULT_MEDIA_TYPE;

		private Map<String, String> queryMap;

		private Map<String, String> headerMap;

		private String requestCharset = DEFAULT_CHARSET;

		private boolean requestLog = DEFAULT_LOG;

		private String responseCharset = DEFAULT_CHARSET;

		private boolean responseLog = DEFAULT_LOG;
	}

}
