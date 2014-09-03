package com.github.cms.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;








import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.Modules;
import com.github.cms.dao.ModulesDao;
import com.github.cms.service.ModuleService;

@Controller
@RequestMapping("/sys/module_edit")
public class ModuleController {
	static final String template = "sys/module_edit";
	@RequestMapping(method = RequestMethod.GET)   
	 public String doGet(HttpServletRequest request, Model model,
			 @RequestParam(value="id", required=false) String idstr) {
		int id = NumberUtils.toInt(idstr, 0);
		Modules module = null;
		if(id>0){
			ModulesDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ModulesDao.class);
			module = dao.get(id);			
		}
		if(module==null){
			module= new Modules();
			module.setId(0);
			module.setEnabled((byte)1);
		}
		
	    model.addAttribute("module", module);
		return template;
	 }
	@RequestMapping(method = RequestMethod.POST)   
	 public String doPost(HttpServletRequest request, Model model , Modules module) {
		ModulesDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ModulesDao.class);
		if(module.getId()==0){
			module.setCreatetime(new Date());
			dao.save(module);
		}else{
			dao.update(module);
		}
	 	return "redirect:module";
	 }

}
