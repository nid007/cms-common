package com.github.cms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.Authorities;
import com.github.cms.bean.GroupAuthorities;
import com.github.cms.bean.Roles;
import com.github.cms.bean.Users;
import com.github.cms.dao.AuthorityDao;
import com.github.cms.dao.GroupAuthorityDao;
import com.github.cms.dao.RoleDao;
import com.github.cms.dao.UserDao;
import com.github.cms.service.bean.RolesExtend;
import com.github.cms.util.Utils;
import com.github.cms.view.ViewHelper;

public class AuthorityService {
	static final Logger log = Logger.getLogger(AuthorityService.class);
	public List<RolesExtend> getGroupRolesList(int groupId) {
		RoleDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(RoleDao.class);
		
		List<Roles> lst = dao.getRoleList();
		
		GroupAuthorityDao gadao =  ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupAuthorityDao.class);
		List<GroupAuthorities> list = gadao.getGroupAuthorities(groupId);
		
		Set<String> set = parseSet(list);
		
		List<RolesExtend> lstExt = parse(lst,set);
		Collections.sort(lstExt);
		return lstExt;
		
	}

	private List<RolesExtend> parse(List<Roles> lst, Set<String> set) {
		List<RolesExtend> l = new ArrayList<RolesExtend>();
		for(Roles role:lst){
			RolesExtend re = new RolesExtend();
			re.setTitle(role.getTitle());
			re.setSelected(set.contains(role.getTitle()));
			l.add(re);
		}
		return l;
	}

	private Set<String> parseSet(List<GroupAuthorities> list) {
		Set<String> set = new HashSet<String>();
		for(GroupAuthorities gm:list){
			set.add(gm.getAuthority());
		}
		return set;
	}

	public void updateGroupRoles(int groupId, String[] arr) {
		if(arr==null){
			arr = new String[]{};
		}
		GroupAuthorityDao gadao =  ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupAuthorityDao.class);
		List<GroupAuthorities> lstdb = gadao.getGroupAuthorities(groupId);
		
		//处理添加
		Set<String> setdb = parseSet(lstdb);
		for(String str:arr){
			if(!setdb.contains(str)){
				GroupAuthorities bean = new GroupAuthorities();
				bean.setAuthority(str);
				bean.setGroupId(groupId);
				gadao.save(bean);
			}
		}
		//处理删除
		Set<String> setform = Utils.parseSet(arr);
		for(GroupAuthorities item:lstdb){
			if(!setform.contains(item.getAuthority())){
				gadao.delete(item);
			}
		}
	}

	public List<RolesExtend> getUserRolesList(String username) {
		RoleDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(RoleDao.class);
		
		List<Roles> lst = dao.getRoleList();
		
		UserDao userDao =  ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserDao.class);
		Users user = userDao.load(username);
		
		Set<String> set = parseAuthset(user.getAuthoritieses());
		
		
		List<RolesExtend> lstExt = parse(lst,set);
		Collections.sort(lstExt);
		return lstExt;
	}

	private Set<String> parseAuthset(Set<Authorities> authoritieses) {
		Set<String> set = new HashSet<String>();
		for(Authorities item:authoritieses){
			set.add(item.getAuthority());
		}
		return set;
		
	}

	public void updateUserRoles(String username, String[] arr) {
		if(arr==null){
			arr = new String[]{};
		}
		AuthorityDao dao =  ContextLoaderListener.getCurrentWebApplicationContext().getBean(AuthorityDao.class);
		
		
		UserDao userDao =   ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserDao.class);
		Users user = userDao.load(username);
		//处理添加
		Set<Authorities> setdb = user.getAuthoritieses();
		for(String str:arr){
			if(!setdb.contains(str)){
				Authorities bean = new Authorities();
				bean.setAuthority(str);
				bean.setUsers(user);
				dao.save(bean);
			}
		}
		
		//处理删除
		Set<String> setform = Utils.parseSet(arr);
		Iterator<Authorities> iter = setdb.iterator();
		while(iter.hasNext()){
			Authorities item=iter.next();
			if(!setform.contains(item.getAuthority())){
				dao.delete(item);
			}
		}
		
	}

	public String resetPass(String username) {
		UserDao dao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserDao.class);
		Users u = new Users();		
		u = dao.get(username);
	
		String newpass = UUID.randomUUID().toString().substring(0,8);
		PasswordEncoder pe = (PasswordEncoder)ContextLoaderListener.getCurrentWebApplicationContext().getBean("passwordEncoder");
		u.setPassword(pe.encode(newpass));
		
		dao.update(u);
		JSONObject json = new JSONObject();
		try {
			json.append("username", username);
			json.append("newpass", newpass);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString(); 
				
	}

}
