package com.github.cms.bean;
// Generated 2014-8-28 15:00:48 by Hibernate Tools 3.2.2.GA



/**
 * Casuser generated by hbm2java
 */
public class Casuser  implements java.io.Serializable {


     private Integer id;
     public static final String ID = "id";
     private String name;
     public static final String NAME = "name";
     private String pass;
     public static final String PASS = "pass";

    public Casuser() {
    }

    public Casuser(String name, String pass) {
       this.name = name;
       this.pass = pass;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }




}


