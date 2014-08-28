package com.github.cms.bean;
// Generated 2014-8-26 16:09:34 by Hibernate Tools 3.2.2.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private String username;
     private String password;
     private boolean enabled;
     private Set authoritieses = new HashSet(0);

    public Users() {
    }

	
    public Users(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
    public Users(String username, String password, boolean enabled, Set authoritieses) {
       this.username = username;
       this.password = password;
       this.enabled = enabled;
       this.authoritieses = authoritieses;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Set getAuthoritieses() {
        return this.authoritieses;
    }
    
    public void setAuthoritieses(Set authoritieses) {
        this.authoritieses = authoritieses;
    }




}

