package com.github.cms.bean;
// Generated 2014-8-29 15:40:16 by Hibernate Tools 3.2.2.GA



/**
 * GroupUser generated by hbm2java
 */
public class GroupUser  implements java.io.Serializable {


     private Integer id;
     public static final String ID = "id";
     private int groupId;
     public static final String GROUPID = "groupId";
     private int userId;
     public static final String USERID = "userId";

    public GroupUser() {
    }

    public GroupUser(int groupId, int userId) {
       this.groupId = groupId;
       this.userId = userId;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }




}

