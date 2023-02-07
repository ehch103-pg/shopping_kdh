package co.kr.shopping.vo;

import lombok.Data;


public class PaginationVO {
	
	private int currentPageNo = 1;
	private int pageSize = 10;
	private int totalPageCount;
	private int totalRecordCount;
	private int firstPageOnIndex;
	private int lastPageOnIndex;
	private int recordCountPerPage = 10;
	private int firstRecordOnPage;
	private int lastRecordOnPage;
	
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPageCount() {
		totalPageCount = ((totalRecordCount-1)/recordCountPerPage) + 1;
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public int getFirstPageOnIndex() {
		firstPageOnIndex = ((getCurrentPageNo() -1) / getPageSize()) * getPageSize() + 1;
		return firstPageOnIndex;
	}
	public void setFirstPageOnIndex(int firstPageOnIndex) {
		this.firstPageOnIndex = firstPageOnIndex;
	}
	public int getLastPageOnIndex() {
		lastPageOnIndex = getFirstPageOnIndex() + getPageSize() - 1;
		if (lastPageOnIndex > getTotalPageCount()) {
			lastPageOnIndex = getTotalPageCount();
		}
		return lastPageOnIndex;
	}
	public void setLastPageOnIndex(int lastPageOnIndex) {
		this.lastPageOnIndex = lastPageOnIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getFirstRecordOnPage() {
		firstRecordOnPage = (getCurrentPageNo() - 1) * getRecordCountPerPage();
		return firstRecordOnPage;
	}
	public void setFirstRecordOnPage(int firstRecordOnPage) {
		this.firstRecordOnPage = firstRecordOnPage;
	}
	public int getLastRecordOnPage() {
		return lastRecordOnPage;
	}
	public void setLastRecordOnPage(int lastRecordOnPage) {
		lastRecordOnPage = getCurrentPageNo() * getRecordCountPerPage();
		this.lastRecordOnPage = lastRecordOnPage;
	}
	
	
	
}
