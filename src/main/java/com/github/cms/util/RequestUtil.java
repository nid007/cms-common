package com.github.cms.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;

import com.github.cms.service.bean.InputBean;

public class RequestUtil {
	static final int defaultPagesize = 20;

	public static InputBean parseInput(HttpServletRequest request) {
		InputBean input = new InputBean();
		input.setNowpage(getInt(request,"nowpage",1));
		input.setPagesize(getInt(request, "pagesize",20));
		if(input.getNowpage()<=0){
			input.setNowpage(1);
		}
		if(input.getPagesize()<0){
			input.setPagesize(defaultPagesize);
		}
		return input;
	}

	private static int getInt(HttpServletRequest request, String name) {
		return getInt(request, name,0);
	}
	private static int getInt(HttpServletRequest request, String name,int def) {
		String str = request.getParameter(name);		
		return NumberUtils.toInt(str,def);
	}

}
