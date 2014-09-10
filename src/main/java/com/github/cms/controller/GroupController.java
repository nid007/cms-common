package com.github.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.Groups;
import com.github.cms.bean.Modules;
import com.github.cms.bean.Users;
import com.github.cms.dao.GroupDao;
import com.github.cms.dao.ModulesDao;
import com.github.cms.dao.UserDao;

@Controller
@RequestMapping("/sys/group_edit")
public class GroupController {
	static final String template = "sys/group_edit";
	@RequestMapping(method = RequestMethod.GET)   
	 public String doGet(HttpServletRequest request, Model model,
			 @RequestParam(value="id", required=false) String idstr) {
		Groups g =null;
		
		int id = NumberUtils.toInt(idstr, 0);
		
		if(id>0){
			GroupDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupDao.class);
			g = dao.get(id);			
		}
		if(g==null){
			g= new Groups();
			g.setId(0);
			
		}
		
	    model.addAttribute("group", g);
		return template;
	 }
	@RequestMapping(method = RequestMethod.POST)   
	 public String doPost(HttpServletRequest request, Model model , Groups g) {
		GroupDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupDao.class);
		if(g.getId()==0){
			dao.save(g);
		}else{
			dao.update(g);
		}
		
	 	return "redirect:group";
	 }

}
