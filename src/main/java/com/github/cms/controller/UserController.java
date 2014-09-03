package com.github.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.Users;
import com.github.cms.dao.UserDao;

@Controller
@RequestMapping("/sys/user_add")
public class UserController {
	static final String template = "sys/user_add";
	@RequestMapping(method = RequestMethod.GET)   
	 public String doGet(HttpServletRequest request, Model model) {
		Users u = new Users();
		u.setEnabled(true);
		
	    model.addAttribute("user", u);
		return template;
	 }
	@RequestMapping(method = RequestMethod.POST)   
	 public String doPost(HttpServletRequest request, Model model , Users u) {
		UserDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserDao.class);
		dao.save(u);
	 	return "redirect:user";
	 }

}
