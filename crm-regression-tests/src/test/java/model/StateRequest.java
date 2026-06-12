package com.tanrikulu.crm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StateRequest {
    
    @JsonProperty("Country")
    private String country;
    
    @JsonProperty("Code")
    private String code;
    
    @JsonProperty("Name")
    private String name;

    public StateRequest() {}

    public StateRequest(String country, String code, String name) {
        this.country = country;
        this.code = code;
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
