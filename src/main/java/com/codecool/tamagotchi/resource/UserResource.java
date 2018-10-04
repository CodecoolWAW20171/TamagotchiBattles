package com.codecool.tamagotchi.resource;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class UserResource {

    @RequestMapping("/user")
    public Object user(OAuth2Authentication authentication) {
        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
        return properties;
    }
}
