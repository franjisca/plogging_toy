package com.myproject.plogging.config.oauth.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Map;


@Builder
public class GoogleUserInfo implements Oauth2UserInfo{

    private Map<String,Object> attributes;

    public GoogleUserInfo() {
    }
    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("sub"));
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get("email"));
    }

    @Override
    public String getUsername() {
        return String.valueOf(attributes.get("name"));
    }
}
