package com.github.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.Groups;
import com.github.cms.dao.GroupDao;
import com.github.cms.service.AuthorityService;
import com.github.cms.service.bean.RolesExtend;

@Controller
@RequestMapping("/sys/user_authority")
public class UserAuthorityController {
	static final String template = "sys/user_authority";
	@RequestMapping(method = RequestMethod.GET)   
	 public String doGet(HttpServletRequest request, Model model,
			 @RequestParam(value="username", required=true) String username) {
			
			model.addAttribute("title", username);
			AuthorityService service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(AuthorityService.class);
			List<RolesExtend> list =   service.getUserRolesList(username);
			model.addAttribute("list", list);
	    
		return template;
	 }
	@RequestMapping(method = RequestMethod.POST)   
	 public String doPost(HttpServletRequest request, Model model ,
			 @RequestParam(value="username", required=true) String username) {
		AuthorityService service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(AuthorityService.class);
		
		service.updateUserRoles(username, request.getParameterValues("roles"));
		
	 	return "redirect:user";
	 }

}
