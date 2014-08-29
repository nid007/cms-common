package com.github.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.dao.ModulesDao;
import com.github.cms.service.ModuleService;

@Controller
public class SysController {
	@RequestMapping("/sys/top")
    public String top() {
        return "sys/top";
    }
	@RequestMapping("/sys/left")
    public String left( Model model) {
		ModuleService service = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ModuleService.class);
		model.addAttribute("list", service.getModulesList());
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
    //    RequestContextUtils.getWebApplicationContext(request).getb
        
   //     ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext();
        
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
