package com.icss.oa.common;

/**
 * 分页工具类
 * @author Administrator
 *
 */
public class Pager {

	private int recordCount; //总记录数
	
	private int pageSize = 9;//每页多少条
	
	private int pageCount; //共几页
	
	private int pageNum;//当前页码
	
	private int start;//当前页的记录起始位置
	
	
	public Pager(int pageNum) {

		this.pageNum = pageNum;

		// 当前页码
		if (this.pageNum < 1)
			this.pageNum = 1;

		// 起始位置
		this.start = (this.pageNum - 1) * this.pageSize + 1;
	}

	public Pager(int recordCount, int pageNum) {	
		
		this.recordCount = recordCount;
		this.pageNum = pageNum;
		
		//共几页
		this.pageCount = (this.recordCount -1) / this.pageSize + 1;
				
		//当前页码
		if (this.pageNum < 1)
			this.pageNum = 1;
		if (this.pageNum > this.pageCount)
			this.pageNum = this.pageCount;
		
		//起始位置
		this.start = (this.pageNum - 1) * this.pageSize + 1;
	}
		
	public Pager(int recordCount, int pageSize, int pageNum) {	
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
				
		//共几页
		this.pageCount = (this.recordCount -1) / this.pageSize + 1;
				
		//当前页码
		if (this.pageNum < 1)
			this.pageNum = 1;
		if (this.pageNum > this.pageCount)
			this.pageNum = this.pageCount;
		
		//起始位置
		this.start = (this.pageNum - 1) * this.pageSize + 1;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	public static void main(String[] args) {		
		Pager pager = new Pager(20,9,3);
		System.out.println(pager.getStart());		
	}
		
}