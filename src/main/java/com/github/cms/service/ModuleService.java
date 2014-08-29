package com.github.cms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoaderListener;
import com.github.cms.bean.Modules;
import com.github.cms.dao.ModulesDao;
import com.github.cms.service.bean.ModulesBean;


public class ModuleService {
	
	public List<ModulesBean> getModulesList(){
		Map<String,ModulesBean> map = new HashMap<String, ModulesBean>();
		ModulesDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ModulesDao.class);
		List<Modules> lMod = dao.getModuleList();
		for(Modules item:lMod){
			String parent = item.getParent();
			if(StringUtils.isEmpty(parent)){
				//根菜单
				ModulesBean bean = map.get(item.getModuleId());
				if(bean==null){
					bean = new ModulesBean();
					
					map.put(item.getModuleId(), bean);
				}
				bean.setModule(item);
			}else{
				//子菜单
				ModulesBean bean = map.get(parent);
				if(bean==null){
					bean = new ModulesBean();
					List<Modules> l = new ArrayList<Modules>();
					l.add(item);
					map.put(parent, bean);
				}else{
					if(bean.getSubList()==null){
						List<Modules> l = new ArrayList<Modules>();
						l.add(item);
						bean.setSubList(l);
					}else{
						bean.getSubList().add(item);
					}
				}
			}			
		}
		List<ModulesBean> list = new ArrayList<ModulesBean>();
		for(Entry<String,ModulesBean> e:map.entrySet()){
			ModulesBean m = e.getValue();
			if(m.getSubList()!=null){
				Collections.sort(m.getSubList(),comp);
			}
			list.add(e.getValue());
		}
		
		Collections.sort(list);
		return list;
		
	}
	private Comparator<Modules> comp = new Comparator<Modules>() {
		
		public int compare(Modules o1, Modules o2) {
			return o1.getModuleId().compareTo(o2.getModuleId());
		}
	};
	
}
