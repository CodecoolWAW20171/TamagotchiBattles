package com.codecool.tamagotchi.battle;

import com.codecool.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.pet.Pet;
import com.codecool.tamagotchi.user.UserController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BattleController {
    private Battle battle = new Battle();

    @PostMapping("/room")
    public String connectRoom(OAuth2Authentication authentication, Model model, @ModelAttribute Pet pet) {
        String username = new UserController().user(authentication);
        pet.setName(username);
        model.addAttribute("pet", pet);

        battle.addPlayer(pet);
        return "battle";
    }

    @MessageMapping("/action")
    @SendTo("/battle/log")
    public BattleLog log(OAuth2Authentication authentication, String message) {
        String username = new UserController().user(authentication);

        return battle.setPlayerAction(username, message);
    }


}
