package com.et.tool;

import java.util.List;

public class PageTool {
	public static void main(String[] args) {
		int curPage=2;
		int totalCount=37;
		int pageCount=5;
		PageTool pt = new PageTool(curPage, totalCount, pageCount);
		System.out.println("下一页"+pt.getNextPage());
		System.out.println("上一页"+pt.getPreviesPage());
		System.out.println("当前页："+curPage);
		System.out.println("总页页"+pt.getTotalPage());
		System.out.println("开始索引"+pt.getStartIndex());
		System.out.println("结束索引"+pt.getEndIndex());
	}
	public PageTool(Integer CurPage,Integer totalCount,Integer pageCount){
		this.CurPage=CurPage;//当前页
		this.totalCount=totalCount;//总记录数(数据库数据)
		this.pageCount=pageCount==null?this.pageCount:pageCount;//每页显示个数
		this.previesPage=(CurPage==1?1:CurPage-1);//上一页
		//再算下一页之前先算出总页数
		this.totalPage=totalCount%this.pageCount==0?totalCount/this.pageCount:totalCount/this.pageCount+1;
		this.nextPage=(CurPage==totalPage)?totalPage:(CurPage+1);
		this.startIndex=(CurPage-1)*this.pageCount+1;
		this.endIndex=CurPage*this.pageCount;
	}
	private Integer startIndex;//结束索引
	private Integer endIndex;//开始索引
	private Integer CurPage;//当前页
	private Integer totalCount;//总记录数
	private Integer pageCount=10;//每页显示个数
	private Integer previesPage;//前一页
	private Integer nextPage;//下一页
	private Integer totalPage;//总页数
	private List data;//存储最终查询的数据
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	public Integer getCurPage() {
		return CurPage;
	}
	public void setCurPage(Integer curPage) {
		CurPage = curPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPreviesPage() {
		return previesPage;
	}
	public void setPreviesPage(Integer previesPage) {
		this.previesPage = previesPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	

}
