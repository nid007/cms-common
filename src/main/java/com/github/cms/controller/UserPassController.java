package com.github.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.Users;
import com.github.cms.dao.UserDao;

@Controller
@RequestMapping("/sys/user_change_pass")
public class UserPassController {
	static final String template = "sys/user_change_pass";
	@RequestMapping(method = RequestMethod.GET)   
	 public String doGet(HttpServletRequest request, Model model) {
	    
		return template;
	 }
	@RequestMapping(method = RequestMethod.POST)   
	 public String doPost(HttpServletRequest request, Model model ,
			 @RequestParam(value="oldpass", required=true) String oldpass,
			 @RequestParam(value="newpass", required=true) String newpass) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserDao.class);
		Users u = dao.get(username);
		PasswordEncoder pe = (PasswordEncoder) ContextLoaderListener.getCurrentWebApplicationContext().getBean("passwordEncoder");
		String message = "";
		if(pe.matches(oldpass, u.getPassword())){
			u.setPassword(pe.encode(newpass));
			dao.update(u);
			message = "修改成功！";
		}else{
			message = "旧密码错误！";
		}
		model.addAttribute("message",message);
		return template;
	 }

}
