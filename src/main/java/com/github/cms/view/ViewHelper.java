package com.github.cms.view;

public class ViewHelper {
	public static String enable(Integer v){
		if(v==0){
			return "否";
		}else if(v==1){
			return "是";
		}else{
			return "unknow";
		}
	}
	public static String enable(boolean v){
		if(v){
			return "是";
		}else{
			return "否";
		}
	}

}
