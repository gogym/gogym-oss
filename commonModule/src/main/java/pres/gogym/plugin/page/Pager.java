package pres.gogym.plugin.page;

import java.io.Serializable;

/**
 * HTTP请求分页参数 Created by JK on 2017/4/19.
 */
public class Pager implements Serializable {
	private static final long serialVersionUID = -6631386289841116116L;

	/**
	 * 开始条数
	 */
	public static final int PAGENUM = 1;
	/**
	 * 查询条数
	 */
	public static final int PAGESIZE = 10;

	/**
	 * 当前页码
	 */
	private int pageNum = PAGENUM;

	/**
	 * 每页查询的条数
	 */
	private int pageSize = PAGESIZE;

	public Pager() {
	}

	public Pager(int pageNum, int pageSize) {
		this.setPageNum(pageNum);
		this.setPageSize(pageSize);
	}

	protected Pager(Pager page) {
		this(page.getPageNum(), page.getPageSize());
	}

	public int getPageNum() {

		return pageNum;
	}

	public void setPageNum(int pageNum) {

		this.pageNum = pageNum;
	}

	public int getPageSize() {

		return pageSize;
	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;
	}

}
