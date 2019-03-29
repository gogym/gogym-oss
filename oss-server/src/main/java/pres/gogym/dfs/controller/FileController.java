package pres.gogym.dfs.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import pres.gogym.common.ResponseJson;
import pres.gogym.dfs.annotation.AccessToken;
import pres.gogym.dfs.module.model.OssAppInfo;
import pres.gogym.dfs.module.model.OssMaterialInfo;
import pres.gogym.dfs.plugin.fastdfs.pool.FastDFSException;
import pres.gogym.dfs.plugin.fastdfs.pool.FastDFSTemplate;
import pres.gogym.dfs.plugin.fastdfs.pool.FastDfsInfo;
import pres.gogym.dfs.service.OssAppInfoService;
import pres.gogym.dfs.service.OssMaterialInfoService;
import pres.gogym.dfs.utils.ImageUtil;
import pres.gogym.plugin.redis.IRedisProxy;
import pres.gogym.plugin.threadpool.ThreadPool;
import pres.gogym.utils.FastJsonUtils;
import pres.gogym.utils.FileUtil;
import pres.gogym.utils.LoggerUtils;
import pres.gogym.utils.NetWorkUtil;

@RestController
@RequestMapping("/file/*")
public class FileController {

	@Autowired
	private FastDFSTemplate dfsTemplate;
	@Autowired
	private OssMaterialInfoService ossMaterialInfoService;
	@Autowired
	private OssAppInfoService ossAppInfoService;
	@Autowired
	private IRedisProxy redisProxy;
	@Autowired
	private ThreadPool threadPool;
	@Autowired
	private HttpServletRequest request;

	
	// ----------------------------------下面接口需要鉴权，可提供给客户端------------------------------------------------------

	@AccessToken
	@RequestMapping(value = "uploadByte", method = RequestMethod.POST)
	public ResponseJson uploadByte(
			@RequestParam("filedata") MultipartFile filedata) {

		if (filedata != null) {
			try {

				String fileName = filedata.getOriginalFilename();
				String suffix = fileName
						.substring(fileName.lastIndexOf(".") + 1);

				FastDfsInfo f = dfsTemplate.upload(filedata.getBytes(), suffix);

				// 从redis中获取数据
				String accessToken = request.getParameter("accessToken");
				String str = redisProxy.get(accessToken);
				OssAppInfo ossAppInfo = FastJsonUtils.toBean(str,
						OssAppInfo.class);
				// 记录文件信息
				OssMaterialInfo ossMaterialInfo = new OssMaterialInfo();
				ossMaterialInfo.setAppId(ossAppInfo.getAppId());
				ossMaterialInfo.setOriginalName(fileName);
				ossMaterialInfo.setStorePath(f.getGroup() + "/" + f.getPath());
				ossMaterialInfo.setExtension(suffix);
				ossMaterialInfo.setLen(filedata.getSize());
				ossMaterialInfo.setByteStr(FileUtil.FormetFileSize(filedata
						.getSize()));
				ossMaterialInfo.setFromIp(NetWorkUtil.getClientIp(request));
				// 把任务扔到线程池中
				threadPool.execute(new Task(ossMaterialInfo));

				Map<String, Object> data = new HashMap<String, Object>();
				data.put("path", f.getGroup() + "/" + f.getPath());
				return ResponseJson.ok(data);

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseJson.error();
			}

		} else {
			return ResponseJson.notParams();
		}
	}

	@AccessToken
	@RequestMapping(value = "batchUploadByte", method = RequestMethod.POST)
	public ResponseJson batchUploadByte(
			@RequestParam("files") List<MultipartFile> files) {

		if (files != null) {

			List<String> paths = new ArrayList<String>();

			for (MultipartFile file : files) {

				String fileName = file.getOriginalFilename();
				String suffix = fileName
						.substring(fileName.lastIndexOf(".") + 1);

				try {
					try {
						FastDfsInfo f = dfsTemplate.upload(file.getBytes(),
								suffix);

						// 从redis中获取数据
						String accessToken = request
								.getParameter("accessToken");
						String str = redisProxy.get(accessToken);
						OssAppInfo ossAppInfo = FastJsonUtils.toBean(str,
								OssAppInfo.class);
						// 记录文件信息
						OssMaterialInfo ossMaterialInfo = new OssMaterialInfo();
						ossMaterialInfo.setAppId(ossAppInfo.getAppId());
						ossMaterialInfo.setOriginalName(fileName);
						ossMaterialInfo.setStorePath(f.getGroup() + "/"
								+ f.getPath());
						ossMaterialInfo.setExtension(suffix);
						ossMaterialInfo.setLen(file.getSize());
						ossMaterialInfo.setByteStr(FileUtil.FormetFileSize(file
								.getSize()));
						ossMaterialInfo.setFromIp(NetWorkUtil
								.getClientIp(request));
						// 把任务扔到线程池中
						threadPool.execute(new Task(ossMaterialInfo));

						paths.add(f.getGroup() + "/" + f.getPath());
					} catch (FastDFSException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("path", paths);
			return ResponseJson.ok(data);

		} else {
			return ResponseJson.notParams();
		}
	}

	/**
	 * 预览文件
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-12-12
	 * @param imagePath
	 * @param local
	 * @return
	 */
	@RequestMapping(value = "getFile/**", method = RequestMethod.GET)
	public void getFile(HttpServletResponse response, HttpServletRequest request) {

		String fileName = extractPathFromPattern(request);

		if (StringUtils.isEmpty(fileName)) {
			return;
		}
		// 设置编码
		response.setCharacterEncoding("UTF-8");

		// 从redis中获取数据
		String accessToken = request.getParameter("accessToken");
		String str = redisProxy.get(accessToken);
		OssAppInfo ossAppInfo = FastJsonUtils.toBean(str, OssAppInfo.class);
		// 把任务扔到线程池中
		threadPool.execute(new ReadTask(ossAppInfo, NetWorkUtil
				.getClientIp(request), fileName));

		try {

			byte[] bytes = dfsTemplate.loadFile(fileName);

			// 判断文件是否存在
			if (bytes != null) {

				// 清空response
				response.reset();
				// 设置response的Header，注意这句，如果开启，默认浏览器会进行下载操作，如果注释掉，浏览器会默认预览。
				// response.addHeader("Content-Disposition",
				// "attachment;filename=" + FileUtil.getOriginalFilename(path));
				OutputStream toClient = new BufferedOutputStream(
						response.getOutputStream());

				String contentType = "";
				// 注意contentType类型
				response.setContentType(contentType);
				response.setContentLength(bytes.length);
				;

				byte[] buf = new byte[1024];
				InputStream in = new ByteArrayInputStream(bytes);

				int L;
				while ((L = in.read(buf)) != -1) {
					// if (buf.length != 0)
					// {
					toClient.write(buf, 0, L);
					// }
				}
				in.close();
				// 写完以后关闭文件流
				toClient.flush();
				toClient.close();
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到相关资源");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (FastDFSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-12-12
	 * @param imagePath
	 * @param local
	 * @return
	 */
	@AccessToken
	@RequestMapping(value = "downloadFile", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, String path) {

		if (StringUtils.isEmpty(path)) {
			return;
		}

		// 设置编码
		response.setCharacterEncoding("UTF-8");

		// 从redis中获取数据
		String accessToken = request.getParameter("accessToken");
		String str = redisProxy.get(accessToken);
		OssAppInfo ossAppInfo = FastJsonUtils.toBean(str, OssAppInfo.class);
		// 把任务扔到线程池中
		threadPool.execute(new ReadTask(ossAppInfo, NetWorkUtil
				.getClientIp(request), path));

		try {

			boolean flag = true;

			// 判断文件是否存在
			if (flag) {

				// 清空response
				response.reset();
				// 设置response的Header
				response.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ FileUtil.getOriginalFilename(path));
				byte[] bytes = dfsTemplate.loadFile(path);
				if (bytes == null) {
					return;
				}

				// 通过文件流的形式写到客户端
				OutputStream toClient = new BufferedOutputStream(
						response.getOutputStream());

				String contentType = "";
				response.setContentType(contentType);
				response.setContentLength(bytes.length);
				byte[] buf = new byte[1024];
				InputStream in = new ByteArrayInputStream(bytes);

				int L;
				while ((L = in.read(buf)) != -1) {
					// if (buf.length != 0)
					// {
					toClient.write(buf, 0, L);
					// }
				}
				in.close();
				// 写完以后关闭文件流
				toClient.flush();
				toClient.close();

			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到相关资源");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (FastDFSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 预览缩略图
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-12-12
	 * @param imagePath
	 * @param local
	 * @return
	 */
	@RequestMapping(value = "previewThumbnail/**", method = RequestMethod.GET)
	public void previewThumbnail(HttpServletResponse response,
			HttpServletRequest request) {

		String fileName = extractPathFromPattern(request);

		// System.out.println(fileName);

		if (StringUtils.isEmpty(fileName)) {
			return;
		}

		if (!FileUtil.getExtensionName(fileName).equalsIgnoreCase("jpg")
				&& !FileUtil.getExtensionName(fileName).equalsIgnoreCase("png")
				&& !FileUtil.getExtensionName(fileName)
						.equalsIgnoreCase("jpeg")) {
			return;
		}

		// 设置编码
		response.setCharacterEncoding("UTF-8");

		// 从redis中获取数据
		String accessToken = request.getParameter("accessToken");
		String str = redisProxy.get(accessToken);
		OssAppInfo ossAppInfo = FastJsonUtils.toBean(str, OssAppInfo.class);
		// 把任务扔到线程池中
		threadPool.execute(new ReadTask(ossAppInfo, NetWorkUtil
				.getClientIp(request), fileName));

		try {

			byte[] bytes = dfsTemplate.loadFile(fileName);
			// 判断文件是否存在
			if (bytes != null) {

				// 清空response
				response.reset();
				OutputStream toClient = new BufferedOutputStream(
						response.getOutputStream());

				// 压缩图片
				byte[] scale = ImageUtil.imgScaleByte(bytes, 0.3);

				String contentType = "";
				// 注意contentType类型
				response.setContentType(contentType);
				response.setContentLength(scale.length);

				byte[] buf = new byte[1024];
				InputStream in = new ByteArrayInputStream(scale);

				int L;
				while ((L = in.read(buf)) != -1) {
					// if (buf.length != 0)
					// {
					toClient.write(buf, 0, L);
					// }
				}
				in.close();
				// 写完以后关闭文件流
				toClient.flush();
				toClient.close();
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到相关资源");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (FastDFSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载缩略图
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-12-12
	 * @param imagePath
	 * @param local
	 * @return
	 */
	@AccessToken
	@RequestMapping(value = "downloadThumbnail/**", method = RequestMethod.GET)
	public void downloadThumbnail(HttpServletResponse response,
			HttpServletRequest request) {

		String fileName = extractPathFromPattern(request);

		if (StringUtils.isEmpty(fileName)) {
			return;
		}

		if (!FileUtil.getExtensionName(fileName).equalsIgnoreCase("jpg")
				&& !FileUtil.getExtensionName(fileName).equalsIgnoreCase("png")
				&& !FileUtil.getExtensionName(fileName)
						.equalsIgnoreCase("jpeg")) {
			return;
		}

		// 设置编码
		response.setCharacterEncoding("UTF-8");

		// 从redis中获取数据
		String accessToken = request.getParameter("accessToken");
		String str = redisProxy.get(accessToken);
		OssAppInfo ossAppInfo = FastJsonUtils.toBean(str, OssAppInfo.class);
		// 把任务扔到线程池中
		threadPool.execute(new ReadTask(ossAppInfo, NetWorkUtil
				.getClientIp(request), fileName));
		try {

			byte[] bytes = dfsTemplate.loadFile(fileName);
			// 判断文件是否存在
			if (bytes != null) {

				// 清空response
				response.reset();

				// 设置response的Header
				response.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ FileUtil.getOriginalFilename(fileName));

				OutputStream toClient = new BufferedOutputStream(
						response.getOutputStream());

				// 压缩图片
				byte[] scale = ImageUtil.imgScaleByte(bytes, 0.3);

				String contentType = "";
				// 注意contentType类型
				response.setContentType(contentType);
				response.setContentLength(scale.length);

				byte[] buf = new byte[1024];
				InputStream in = new ByteArrayInputStream(scale);

				int L;
				while ((L = in.read(buf)) != -1) {
					// if (buf.length != 0)
					// {
					toClient.write(buf, 0, L);
					// }
				}
				in.close();
				// 写完以后关闭文件流
				toClient.flush();
				toClient.close();
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到相关资源");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (FastDFSException e) {
			e.printStackTrace();
		}
	}

	// 把指定URL后的字符串全部截断当成参数
	// 这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
	private String extractPathFromPattern(final HttpServletRequest request) {
		String path = (String) request
				.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request
				.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern,
				path);
	}

	// 异步任务类,为了节省操作数据库的时间，提高响应效率
	class Task implements Runnable {
		private OssMaterialInfo ossMaterialInfo;

		public Task(OssMaterialInfo ossMaterialInfo) {
			this.ossMaterialInfo = ossMaterialInfo;
		}

		@Override
		public void run() {
			try {
				ossMaterialInfoService.insertOne(ossMaterialInfo);
			} catch (Exception e) {
				LoggerUtils.error(getClass(), "上传失败", e);
			}
		}
	}

	class ReadTask implements Runnable {
		private OssAppInfo ossAppInfo;
		String fromIp;
		String path;

		public ReadTask(OssAppInfo ossAppInfo, String fromIp, String path) {
			this.ossAppInfo = ossAppInfo;
			this.fromIp = fromIp;
			this.path = path;
		}

		@Override
		public void run() {
			ossAppInfoService.updateFuncRead(ossAppInfo, fromIp, path);
		}
	}
}
