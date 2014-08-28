package com.github.cms.bean;
// Generated 2014-8-26 16:09:34 by Hibernate Tools 3.2.2.GA



/**
 * ModuleAuthoritiesId generated by hbm2java
 */
public class ModuleAuthoritiesId  implements java.io.Serializable {


     private String moduleId;
     private String authority;

    public ModuleAuthoritiesId() {
    }

    public ModuleAuthoritiesId(String moduleId, String authority) {
       this.moduleId = moduleId;
       this.authority = authority;
    }
   
    public String getModuleId() {
        return this.moduleId;
    }
    
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
    public String getAuthority() {
        return this.authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ModuleAuthoritiesId) ) return false;
		 ModuleAuthoritiesId castOther = ( ModuleAuthoritiesId ) other; 
         
		 return ( (this.getModuleId()==castOther.getModuleId()) || ( this.getModuleId()!=null && castOther.getModuleId()!=null && this.getModuleId().equals(castOther.getModuleId()) ) )
 && ( (this.getAuthority()==castOther.getAuthority()) || ( this.getAuthority()!=null && castOther.getAuthority()!=null && this.getAuthority().equals(castOther.getAuthority()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getModuleId() == null ? 0 : this.getModuleId().hashCode() );
         result = 37 * result + ( getAuthority() == null ? 0 : this.getAuthority().hashCode() );
         return result;
   }   


}

