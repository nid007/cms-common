package com.github.cms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.GroupMembers;
import com.github.cms.bean.Users;
import com.github.cms.dao.GroupMemberDao;
import com.github.cms.dao.UserDao;
import com.github.cms.service.bean.UsersExtend;
import com.github.cms.util.Utils;


public class GroupMemberService {
	public List<UsersExtend> getUserList(int groupId){
		UserDao userdao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserDao.class);
		
		List<Users> lst = userdao.getUsers(); 
		
		GroupMemberDao gmdao =  ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupMemberDao.class);
		List<GroupMembers> list = gmdao.getGroupMembers(groupId);
		
		Set<String> set = parseSet(list);
		
		List<UsersExtend> lstExt = parse(lst,set);
		Collections.sort(lstExt);
		return lstExt;
		
	}

	private Set<String> parseSet(List<GroupMembers> list) {
		Set<String> set = new HashSet<String>();
		for(GroupMembers gm:list){
			set.add(gm.getUsername());
		}
		return set;
	}
	

	private List<UsersExtend> parse(List<Users> lst, Set<String> set) {
		List<UsersExtend> l = new ArrayList<UsersExtend>();
		for(Users u:lst){
			UsersExtend ue = new UsersExtend();
			ue.setUsername(u.getUsername());
			ue.setSelected(set.contains(u.getUsername()));
			l.add(ue);
		}
		return l;
	}

	public void updateUsers(int id, String[] userarr) {
		GroupMemberDao gmdao =  ContextLoaderListener.getCurrentWebApplicationContext().getBean(GroupMemberDao.class);
		List<GroupMembers> lstdb = gmdao.getGroupMembers(id);
		
		//处理添加
		Set<String> setdb = parseSet(lstdb);
		for(String user:userarr){
			if(!setdb.contains(user)){
				GroupMembers gm = new GroupMembers();
				gm.setGroupId(id);
				gm.setUsername(user);
				gmdao.save(gm);
			}
		}
		//处理删除
		Set<String> setform = Utils.parseSet(userarr);
		for(GroupMembers gm:lstdb){
			if(!setform.contains(gm.getUsername())){
				gmdao.delete(gm);
			}
		}
	}
	
}
