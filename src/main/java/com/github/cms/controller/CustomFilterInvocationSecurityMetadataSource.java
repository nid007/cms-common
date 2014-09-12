package com.github.cms.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.Modules;
import com.github.cms.dao.ModulesDao;

public class CustomFilterInvocationSecurityMetadataSource 
	implements FilterInvocationSecurityMetadataSource {
	
	private Map<String, Collection<ConfigAttribute>> resourceMap;
	
	Collection<ConfigAttribute> defaultConfig = new ArrayList<ConfigAttribute>();
	Collection<ConfigAttribute> loginConfig = new ArrayList<ConfigAttribute>();
	
	public CustomFilterInvocationSecurityMetadataSource(){
		ConfigAttribute config = new SecurityConfig("ROLE_USER");
		defaultConfig.add(config);
		ConfigAttribute c = new SecurityConfig("IS_AUTHENTICATED_ANONYMOUSLY");
		loginConfig.add(c);
		
		resourceMap = loadResourceMatchAuthority();
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		//url 只取问号前的部分
		url = mytrim(url);
        if (resourceMap == null || resourceMap.size()==0) {

        	resourceMap = loadResourceMatchAuthority();

        }
        Collection<ConfigAttribute> config =  resourceMap.get(url);
        if(config==null){
        	if(url.equals("/sys/login")){
        		return loginConfig;
        	}
        	config=defaultConfig;
        }
        return config;
	}
	private String mytrim(String url) {
		if(url==null){
			return null;
		}
		int i= url.indexOf('?');
		if(i>0){
			return url.substring(0,i);
		}
		return url;
	}

	public void reload(){
		resourceMap = loadResourceMatchAuthority();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		
		return true;
	}
	private Map<String, Collection<ConfigAttribute>> loadResourceMatchAuthority() {

        Map<String, Collection<ConfigAttribute>> map = new HashMap<String, Collection<ConfigAttribute>>();


        Map<String, String> configs = getResourcesConfig();
        if(configs==null){
        	return null;
        }

        for (Entry<String, String> entry : configs.entrySet()) {

            Collection<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
            if(StringUtils.isEmpty(entry.getValue())){
            	continue;
            }
            String[] vals = entry.getValue().split(",");

            for (String val : vals) {

                ConfigAttribute config = new SecurityConfig(val);

                list.add(config);

            }

            map.put(entry.getKey(), list);

        }

        return map;

    }


    /**


     * @return

     */

    private Map<String, String> getResourcesConfig() {
    	
        Map<String, String> map = new HashMap<String, String>();
        if( ContextLoaderListener.getCurrentWebApplicationContext()!=null){
	        ModulesDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ModulesDao.class);
	        List<Modules> list =  dao.getModuleList(true);
	        for(Modules m:list){
	        	map.put(mytrim(m.getLink()), m.getAuthority());
	        }
        }
        return map;

    }
}
