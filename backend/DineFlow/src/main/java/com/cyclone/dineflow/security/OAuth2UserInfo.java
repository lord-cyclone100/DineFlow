package com.cyclone.dineflow.security;

import java.util.Map;

public class OAuth2UserInfo {
    private final Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getGoogleId(){
        return attributes.get("sub").toString();
    }

    public String getName(){
        return attributes.get("name").toString();
    }

    public String getEmail(){
        return attributes.get("email").toString();
    }
}
