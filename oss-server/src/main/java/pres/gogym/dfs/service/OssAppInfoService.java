/*
 * 文件名：OssAppInfoService.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;

import pres.gogym.dfs.module.model.OssAppInfo;

public interface OssAppInfoService {

	/**
	 * 
	 * Description: 添加一个应用
	 * 
	 * @param ossAppInfo
	 * @return
	 * @see
	 */
	public String insterOne(OssAppInfo ossAppInfo);

	/**
	 * 
	 * Description: 查找应用列表
	 * 
	 * @param ossAppInfo
	 * @return
	 * @see
	 */
	public PageQuery<OssAppInfo> findAppList(OssAppInfo ossAppInfo, int start,
			int size);

	public List<OssAppInfo> findAppListNoPage(OssAppInfo ossAppInfo);

	/**
	 * 
	 * Description: 删除一个应用
	 * 
	 * @param id
	 * @return
	 * @see
	 */
	public Integer delById(String id);

	/**
	 * 
	 * Description: 更新一个应用
	 * 
	 * @param ossAppInfo
	 * @return
	 * @see
	 */
	public Integer updateOne(OssAppInfo ossAppInfo);

	/**
	 * 
	 * Description: 查找一个应用
	 * 
	 * @param ossAppInfo
	 * @return
	 * @see
	 */
	public OssAppInfo findOne(OssAppInfo ossAppInfo);

	/**
	 * 
	 * Description: 更新读取次数
	 * 
	 * @param ossAppInfo
	 * @return
	 * @see
	 */
	public Integer updateFuncRead(OssAppInfo ossAppInfo, String fromIp,
			String path);

}
