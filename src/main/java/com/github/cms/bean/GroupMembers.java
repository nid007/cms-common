package com.github.cms.bean;
// Generated 2014-9-10 14:52:54 by Hibernate Tools 3.2.2.GA



/**
 * GroupMembers generated by hbm2java
 */
public class GroupMembers  implements java.io.Serializable {


     private Long id;
     public static final String ID = "id";
     private String username;
     public static final String USERNAME = "username";
     private int groupId;
     public static final String GROUPID = "groupId";

    public GroupMembers() {
    }

    public GroupMembers(String username, int groupId) {
       this.username = username;
       this.groupId = groupId;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public int getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }




}


