package com.github.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.Roles;
import com.github.cms.dao.RoleDao;

@Controller
@RequestMapping("/sys/role_edit")
public class RoleController {
	static final String template = "sys/role_edit";
	@RequestMapping(method = RequestMethod.GET)   
	 public String doGet(HttpServletRequest request, Model model,
			 @RequestParam(value="id", required=false) String idstr) {
		Roles g =null;
		
		int id = NumberUtils.toInt(idstr, 0);
		
		if(id>0){
			RoleDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(RoleDao.class);
			g = dao.get(id);			
		}
		if(g==null){
			g= new Roles();
			g.setId(0);
			
		}
		
	    model.addAttribute("role", g);
		return template;
	 }
	@RequestMapping(method = RequestMethod.POST)   
	 public String doPost(HttpServletRequest request, Model model , Roles r) {
		RoleDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(RoleDao.class);
		if(r.getId()==0){
			dao.save(r);
		}else{
			dao.update(r);
		}
		
	 	return "redirect:role";
	 }

}
