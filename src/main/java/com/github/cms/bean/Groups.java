package com.github.cms.bean;
// Generated 2014-9-10 14:52:54 by Hibernate Tools 3.2.2.GA



/**
 * Groups generated by hbm2java
 */
public class Groups  implements java.io.Serializable {


     private Integer id;
     public static final String ID = "id";
     private String groupName;
     public static final String GROUPNAME = "groupName";

    public Groups() {
    }

    public Groups(String groupName) {
       this.groupName = groupName;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getGroupName() {
        return this.groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }




}


