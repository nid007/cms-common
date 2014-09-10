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
import com.github.cms.bean.Modules;
import com.github.cms.bean.Users;
import com.github.cms.dao.GroupDao;
import com.github.cms.dao.ModulesDao;
import com.github.cms.dao.UserDao;
import com.github.cms.service.GroupMemberService;
import com.github.cms.service.bean.UsersExtend;

@Controller
@RequestMapping("/sys/group_member")
public class GroupMemberController {
	static final String template = "sys/group_member";
	@RequestMapping(method = RequestMethod.GET)   
	 public String doGet(HttpServletRequest request, Model model,
			 @RequestParam(value="id", required=false) String idstr) {
		int id = NumberUtils.toInt(idstr, 0);
		
		if(id>0){
			GroupDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupDao.class);
			Groups g = dao.get(id);
			if(g!=null){
				model.addAttribute("title", g.getGroupName());
			}
			GroupMemberService service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupMemberService.class);
			List<UsersExtend> list =   service.getUserList(id);
			model.addAttribute("list", list);
		}
		
	    
		return template;
	 }
	@RequestMapping(method = RequestMethod.POST)   
	 public String doPost(HttpServletRequest request, Model model ,
			 @RequestParam(value="id", required=false) String idstr) {
		GroupMemberService service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupMemberService.class);
		int id = NumberUtils.toInt(idstr);
		service.updateUsers(id, request.getParameterValues("users"));
		
	 	return "redirect:group";
	 }

}
