package com.codecool.tamagotchi.controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class User {

    @RequestMapping("/user")
    public Object user(OAuth2Authentication authentication) {
        if (authentication != null) {
                LinkedHashMap<String, Object> properties =
                        (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

                return properties.get("name");
        }
        return null;
    }
}
