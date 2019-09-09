package com.hyxy.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;

    private String rolename;

    private String jurisdictionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getJurisdictionid() {
        return jurisdictionid;
    }

    public void setJurisdictionid(String jurisdictionid) {
        this.jurisdictionid = jurisdictionid == null ? null : jurisdictionid.trim();
    }
}