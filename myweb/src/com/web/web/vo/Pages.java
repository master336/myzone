package com.web.web.vo;


public class Pages {
	public static final int defaultPageSize = 10;
	int pageSize;//每页条数
	int start;//开始条数
	int totalCount;//总条数
	int totalPageCount;//总页数
	int pageNo;//当前页号
	boolean hasNextPage;//是否有下一页
	boolean hasPreviousPage;//是否有上一页

	public Pages(Integer pageNo, Integer pageSize, Integer totalCount) {
		this(pageNo, pageSize, totalCount,null);
	}
	public Pages(Integer pageNo, Integer pageSize, Integer totalCount,Object os) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPageCount = totalCount / pageSize + (totalCount % pageSize>0?1:0);
		this.hasNextPage = totalPageCount>pageNo;
		this.hasPreviousPage = pageNo>1;
		this.object = os;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}



	Object object;//每页存储的对象
}
