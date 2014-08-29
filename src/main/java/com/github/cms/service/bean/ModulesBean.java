package com.github.cms.service.bean;

import java.util.List;

import com.github.cms.bean.Modules;

public class ModulesBean implements Comparable<ModulesBean>{
	private Modules module;
	private List<Modules> subList;

	public Modules getModule() {
		return module;
	}

	public void setModule(Modules module) {
		this.module = module;
	}

	public List<Modules> getSubList() {
		return subList;
	}

	public void setSubList(List<Modules> subList) {
		this.subList = subList;
	}

	public int compareTo(ModulesBean o) {
		if(module==null && o.module==null){
			return 0;
		}else if(module==null){
			return -1;
		}else if(o.module==null){
			return 1;
		}
		
		return module.getModuleId().compareTo(o.getModule().getModuleId());
	}
	
}
