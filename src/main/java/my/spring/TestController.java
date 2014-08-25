package my.spring;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class TestController {
	@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        String[] arr = new String[]{"this","is","bbb","test"};
        model.addAttribute("atts", arr);
        return "greeting";
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
	@RequestMapping("/login")
    public String login(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        String[] arr = new String[]{"this","is","bbb","test"};
        model.addAttribute("atts", arr);
        
       
        return "login";
        
    }

}
