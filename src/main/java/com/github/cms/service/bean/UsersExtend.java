package com.github.cms.service.bean;

import com.github.cms.bean.Users;

public class UsersExtend extends Users implements Comparable<UsersExtend>{
	
	boolean selected =false;
	

	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	public int compareTo(UsersExtend o) {
		if(selected==o.selected){
			return getUsername().compareTo(o.getUsername());
		}if(selected){
			return -1;
		}else{
			return 1;
		}
		
	}

}
