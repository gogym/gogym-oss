/*
 * 文件名：OssMaterialInfoController.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月27日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import pres.gogym.common.ResponseJson;
import pres.gogym.dfs.module.model.OssMaterialInfo;
import pres.gogym.dfs.plugin.fastdfs.pool.FastDFSException;
import pres.gogym.dfs.plugin.fastdfs.pool.FastDFSTemplate;
import pres.gogym.dfs.plugin.fastdfs.pool.FastDfsInfo;
import pres.gogym.dfs.service.OssMaterialInfoService;
import pres.gogym.utils.BeanUtil;
import pres.gogym.utils.FileUtil;

@RestController
@RequestMapping("/ossMaterialInfo/*")
public class OssMaterialInfoController {

	@Autowired
	private OssMaterialInfoService ossMaterialInfoService;

	@Autowired
	private FastDFSTemplate dfsTemplate;

	/**
	 * 
	 * Description: 分页获取文件列表
	 * 
	 * @param ossMaterialInfo
	 * @param start
	 * @param size
	 * @return
	 * @see
	 */
	@PostMapping("findList")
	public ResponseJson findList(OssMaterialInfo ossMaterialInfo,
			Integer start, Integer size) {

		if (BeanUtil.checkObjFieldIsNull(ossMaterialInfo)) {
			return ResponseJson.notParams();
		}

		if (null == start) {
			start = 1;
		}
		if (null == size) {
			size = 10;
		}

		PageQuery<OssMaterialInfo> query = ossMaterialInfoService.findList(
				ossMaterialInfo, start, size);

		Map<Object, Object> res = new HashMap<Object, Object>();
		res.put("list", query.getList());
		res.put("total", query.getTotalRow());

		return ResponseJson.ok(res);

	}

	/**
	 * 
	 * Description: 删除一个文件
	 * 
	 * @param id
	 * @return
	 * @see
	 */
	@PostMapping("delOne")
	public ResponseJson delOne(OssMaterialInfo ossMaterialInfo) {

		if (StringUtils.isEmpty(ossMaterialInfo.getId())
				|| StringUtils.isEmpty(ossMaterialInfo.getStorePath())) {
			return ResponseJson.notParams();
		}

		String[] s = dfsTemplate.split_file_id(ossMaterialInfo.getStorePath());

		try {
			dfsTemplate.deleteFile(s[0], s[1]);
			ossMaterialInfoService.delOne(ossMaterialInfo.getId());

			return ResponseJson.ok();

		} catch (FastDFSException e) {
			e.printStackTrace();
		}
		return ResponseJson.error();
	}

	/**
	 * 上传文件
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-12-12
	 * @return
	 */
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public ResponseJson uploadFile(@RequestParam MultipartFile filedata) {

		if (filedata != null && !filedata.isEmpty()) {
			try {

				String fileName = filedata.getOriginalFilename();
				String suffix = fileName
						.substring(fileName.lastIndexOf(".") + 1);
				// 上传到文件系统
				FastDfsInfo f = dfsTemplate.upload(filedata.getBytes(), suffix);

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
	@RequestMapping(value = "downFile/**", method = RequestMethod.GET)
	public void downFile(HttpServletResponse response,
			HttpServletRequest request) {

		String fileName = extractPathFromPattern(request);

		if (StringUtils.isEmpty(fileName)) {
			return;
		}
		// 设置编码
		response.setCharacterEncoding("UTF-8");

		try {

			byte[] bytes = dfsTemplate.loadFile(fileName);
			// 判断文件是否存在
			if (bytes != null) {

				// 清空response
				response.reset();
				// 设置response的Header，注意这句，如果开启，默认浏览器会进行下载操作，如果注释掉，浏览器会默认预览。
				response.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ FileUtil.getOriginalFilename(fileName));
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

}
