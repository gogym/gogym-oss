/*
 * 文件名：OssDeveloperService.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年3月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.service;

import java.util.List;

import pres.gogym.dfs.module.model.OssDeveloper;
import pres.gogym.dfs.module.vo.HomeCountVo;

public interface OssDeveloperService {

	/**
	 * 
	 * Description: 查找全部开发者
	 * 
	 * @return
	 * @see
	 */
	public List<OssDeveloper> findAll(int start, int size);

	/**
	 * 
	 * Description: 查询一个开发者
	 * 
	 * @param ossDeveloper
	 * @return
	 * @see
	 */
	public OssDeveloper findOne(OssDeveloper ossDeveloper);

	/**
	 * 
	 * Description: 通过用户名查找
	 * 
	 * @param username
	 * @return
	 * @see
	 */
	public OssDeveloper findByUsername(String username);

	/**
	 * 
	 * Description: 添加一个新的开发者
	 * 
	 * @param ossDeveloper
	 * @return
	 * @see
	 */
	public String insterOne(OssDeveloper ossDeveloper);

	/**
	 * 
	 * Description: 更新一个开发者
	 * 
	 * @param ossDeveloper
	 * @return
	 * @see
	 */
	public Integer updateOne(OssDeveloper ossDeveloper);

	/**
	 * 
	 * Description: 统计首页数据
	 * 
	 * @return
	 * @see
	 */
	public HomeCountVo countData(String devId);

}
