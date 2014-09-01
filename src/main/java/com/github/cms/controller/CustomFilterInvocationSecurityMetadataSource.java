package com.github.cms.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class CustomFilterInvocationSecurityMetadataSource 
	implements FilterInvocationSecurityMetadataSource {
	
	private Map<String, Collection<ConfigAttribute>> resourceMap;
	
	public CustomFilterInvocationSecurityMetadataSource(){
		 resourceMap = loadResourceMatchAuthority();
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		 String url = ((FilterInvocation) object).getRequestUrl();

	       // System.out.println("requestUrl is " + url);

	        if (resourceMap == null) {

	            loadResourceMatchAuthority();

	        }

	        return resourceMap.get(url);
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

        for (Entry<String, String> entry : configs.entrySet()) {

            Collection<ConfigAttribute> list = new ArrayList<ConfigAttribute>();

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

        map.put("/", "ROLE_USER");

        map.put("/greeting", "ROLE_ADMIN");

        return map;

    }
}
