package com.github.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.Users;
import com.github.cms.dao.GroupDao;
import com.github.cms.dao.ModulesDao;
import com.github.cms.dao.RoleDao;
import com.github.cms.dao.UserDao;
import com.github.cms.service.ModuleService;
import com.github.cms.service.bean.InputBean;
import com.github.cms.service.bean.PagerResult;
import com.github.cms.util.PageUtil;
import com.github.cms.util.RequestUtil;
import com.github.cms.view.ViewHelper;

@Controller
public class SysController {
	

	@RequestMapping("/sys/user_mod_enable")
    public @ResponseBody String user_mod_enable(HttpServletRequest request, Model model,
    		 @RequestParam(value="username", required=false) String username) {
		UserDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserDao.class);
		Users u = new Users();
		if(!StringUtils.isEmpty(username)){
			u = dao.get(username);
			if(u!=null){
				u.setEnabled(!u.isEnabled());
				dao.update(u);
			}
		}
		JSONObject json = new JSONObject();
		try {
			json.append("username", username);
			json.append("enabled", u.isEnabled());
			json.append("enableStr", ViewHelper.enable(u.isEnabled()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return json.toString(); 
    }
	
	
	@RequestMapping("/sys/user")
    public String user(HttpServletRequest request, Model model) {
		UserDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserDao.class);
		InputBean input = RequestUtil.parseInput(request);
		PagerResult<Users> result =  dao.getPagerResult(input);
		model.addAttribute("result",result);
        String page = PageUtil.generatePageHtml(result, request);
        model.addAttribute("page", page);
        return "sys/user";
    }

	@RequestMapping("/sys/user_del")
	 public String userDel(HttpServletRequest request, Model model,
			 @RequestParam(value="username", required=false) String username) {
		UserDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserDao.class);
		dao.deleteByKey(username);
	 	return "redirect:user";
	 }

	@RequestMapping("/sys/group")
    public String group( Model model) {
		GroupDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupDao.class);
		model.addAttribute("list", dao.getGroupList());
       
        return "sys/group";
    }

	@RequestMapping("/sys/group_del")
	 public String groupDel(HttpServletRequest request, Model model,
			 @RequestParam(value="id", required=false) String idstr) {
		GroupDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupDao.class);
		int id = NumberUtils.toInt(idstr, 0);
		dao.deleteByKey(id);
	 	return "redirect:group";
	 }
	@RequestMapping("/sys/role")
    public String role( Model model) {
		RoleDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(RoleDao.class);
		model.addAttribute("list", dao.getRoleList());
       
        return "sys/role";
    }

	@RequestMapping("/sys/role_del")
	 public String roleDel(HttpServletRequest request, Model model,
			 @RequestParam(value="id", required=false) String idstr) {
		RoleDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(RoleDao.class);
		int id = NumberUtils.toInt(idstr, 0);
		dao.deleteByKey(id);
	 	return "redirect:role";
	 }
	

	@RequestMapping("/sys/module")
    public String module( Model model) {
		ModuleService service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ModuleService.class);
		model.addAttribute("list", service.getModulesList(true));
       
        return "sys/module";
    }

	@RequestMapping("/sys/module_del")
	 public String moduleDel(HttpServletRequest request, Model model,
			 @RequestParam(value="id", required=false) String idstr) {
		ModulesDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ModulesDao.class);
		int id = NumberUtils.toInt(idstr, 0);
		dao.deleteByKey(id);
	 	return "redirect:module";
	 }
	@RequestMapping("/sys/top")
    public String top() {
        return "sys/top";
    }
	@RequestMapping("/sys/left")
    public String left( Model model) {
		ModuleService service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ModuleService.class);
		model.addAttribute("list", service.getModulesList(false));
        return "sys/left";
    }
	@RequestMapping("/sys/leftbar")
    public String leftbar() {       
        return "sys/leftbar";
    }
	@RequestMapping("/sys/welcome")
    public String welcome() {       
        return "sys/welcome";
    }
	@RequestMapping("/sys/footer")
    public String footer() {       
        return "sys/footer";
    }
	@RequestMapping("/sys/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        String[] arr = new String[]{"this","is","bbb","test"};
        model.addAttribute("atts", arr);
        return "sys/greeting";
    }
	@RequestMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model,HttpServletRequest request) {
        model.addAttribute("name", name);
        String[] arr = new String[]{"this","is","bbb","test"};
        model.addAttribute("atts", arr);
        model.addAttribute("url",request.getRequestURI());
        return "index";
    }
	@RequestMapping("/sys/login")
    public String login(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        String[] arr = new String[]{"this","is","bbb","test"};
        model.addAttribute("atts", arr);
        
       
        return "sys/login";
        
    }

}
