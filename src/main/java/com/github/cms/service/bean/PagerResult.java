package com.github.cms.service.bean;

import java.util.List;

import com.github.cms.bean.Users;

public class PagerResult<T> {
	
	int nowpage;
	int pagesize;
	long total;
	
	List<T> result;


	public PagerResult() {
		
	}
	
	public PagerResult(int nowpage, int pagesize, long total, List<T> result) {
		this.nowpage = nowpage;
		this.pagesize = pagesize;
		this.total = total;
		this.result = result;
	}

	public PagerResult(InputBean input, long total, List<T> result) {
		this.nowpage = input.getNowpage();
		this.pagesize = input.getPagesize();
		this.total = total;
		this.result = result;
	}

	public int getNowpage() {
		return nowpage;
	}

	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
	
	
	
}
