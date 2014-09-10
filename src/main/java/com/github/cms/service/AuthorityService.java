package com.github.cms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.context.ContextLoaderListener;

import com.github.cms.bean.GroupAuthorities;
import com.github.cms.bean.Roles;
import com.github.cms.dao.GroupAuthorityDao;
import com.github.cms.dao.RoleDao;
import com.github.cms.service.bean.RolesExtend;
import com.github.cms.util.Utils;

public class AuthorityService {

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

}
