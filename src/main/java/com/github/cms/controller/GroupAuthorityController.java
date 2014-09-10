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
@RequestMapping("/sys/group_authority")
public class GroupAuthorityController {
	static final String template = "sys/group_authority";
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
			AuthorityService service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(AuthorityService.class);
			List<RolesExtend> list =   service.getGroupRolesList(id);
			model.addAttribute("list", list);
		}
	    
		return template;
	 }
	@RequestMapping(method = RequestMethod.POST)   
	 public String doPost(HttpServletRequest request, Model model ,
			 @RequestParam(value="id", required=false) String idstr) {
		AuthorityService service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(AuthorityService.class);
		int id = NumberUtils.toInt(idstr);
		service.updateGroupRoles(id, request.getParameterValues("roles"));
		
	 	return "redirect:group";
	 }

}
