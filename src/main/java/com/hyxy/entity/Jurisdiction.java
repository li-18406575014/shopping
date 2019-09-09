package com.hyxy.entity;

import java.io.Serializable;

public class Jurisdiction implements Serializable {
    private Integer id;

    private String jurisdiction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction == null ? null : jurisdiction.trim();
    }
}