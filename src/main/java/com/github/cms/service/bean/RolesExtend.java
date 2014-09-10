package com.github.cms.service.bean;

import com.github.cms.bean.Roles;

public class RolesExtend extends Roles implements Comparable<RolesExtend>{
	
	boolean selected =false;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int compareTo(RolesExtend o) {
		if(selected==o.selected){
			return getTitle().compareTo(o.getTitle());
		}if(selected){
			return -1;
		}else{
			return 1;
		}		
	}

}
