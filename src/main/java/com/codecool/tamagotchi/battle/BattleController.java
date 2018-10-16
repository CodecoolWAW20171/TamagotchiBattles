package com.codecool.tamagotchi.battle;

import com.codecool.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.pet.Pet;
import com.codecool.tamagotchi.user.UserController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BattleController {
    Battle battle = new Battle();

    @PostMapping("/room")
    public String petSubmit(OAuth2Authentication authentication, @ModelAttribute Pet pet) {
        String username = new UserController().user(authentication);

        pet.setName(username);
        if (battle.getFirstPlayer() == null) {
            battle.setFirstPlayer(pet);
        }
        else if (battle.getSecondPlayer() == null) {
            battle.setSecondPlayer(pet);
        }
        return "battle";
    }

    @PostMapping("/room/action")
    public void action(OAuth2Authentication authentication) {
        String username = new UserController().user(authentication);

        if (username.equals(battle.getFirstPlayer().getName())) {
            System.out.println("First player attack");
            battle.getFirstPlayer().setState(Action.ATTACK);
        } else if (username.equals(battle.getSecondPlayer().getName())) {
            System.out.println("Second player attack");
            battle.getSecondPlayer().setState(Action.ATTACK);
        }
    }

    @MessageMapping("/action")
    @SendTo("/battle/log")
    public BattleLog log(String message) throws Exception {
        return new BattleLog(message);
    }


}
