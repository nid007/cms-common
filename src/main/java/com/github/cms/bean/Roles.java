package com.github.cms.bean;
// Generated 2014-8-29 15:40:16 by Hibernate Tools 3.2.2.GA



/**
 * Roles generated by hbm2java
 */
public class Roles  implements java.io.Serializable {


     private Integer id;
     public static final String ID = "id";
     private String title;
     public static final String TITLE = "title";

    public Roles() {
    }

    public Roles(String title) {
       this.title = title;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }




}


