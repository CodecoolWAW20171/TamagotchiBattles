package com.codecool.tamagotchi.battle;

import com.codecool.tamagotchi.pet.Pet;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;

@Controller
public class BattleController {
    Battle battle = new Battle();

    @PostMapping("/createPet")
    public String petSubmit(OAuth2Authentication authentication, @ModelAttribute Pet pet) {
        pet.setName((String) ((LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails()).get("name"));
        System.out.println(pet.toString());
        if (battle.getFirstPlayer() == null) battle.setFirstPlayer(pet);
        else if (battle.getSecondPlayer() == null) battle.setSecondPlayer(pet);
        return "pet";
    }
}
