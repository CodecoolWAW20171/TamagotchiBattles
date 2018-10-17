package com.codecool.tamagotchi.user;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class UserController {

    @RequestMapping("/user")
    public LinkedHashMap<String, Object> user(OAuth2Authentication authentication) {
        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
        return properties;
    }

    public String getUsername(OAuth2Authentication authentication) {
        return (String) user(authentication).get("name");
    }

    public String getUserId(OAuth2Authentication authentication) {
        return (String) user(authentication).get("id");
    }

}
