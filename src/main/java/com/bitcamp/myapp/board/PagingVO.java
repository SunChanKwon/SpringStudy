package com.bitcamp.myapp.board;

public class PagingVO {
	private int totalRecord;//총 레코드 수
	private int nowPage=1; //현재 페이지
	private int totalPage; //총 페이지 수
	private int onePageRecord=5; //한페이지당 레코드 수
	
	//검색어
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		
		//총페이지 수
		totalPage = (int)Math.ceil(totalRecord/(double)onePageRecord);
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getOnePageRecord() {
		return onePageRecord;
	}
	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}
	
}
