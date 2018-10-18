package com.codecool.tamagotchi.battle;

import com.codecool.tamagotchi.enumerations.Type;
import com.codecool.tamagotchi.pet.Pet;
import com.codecool.tamagotchi.pet.PetRepository;
import com.codecool.tamagotchi.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BattleController {

    private final BattleRepository repository;
    private final PetRepository petRepository;

    @Autowired
    public BattleController(BattleRepository repository, PetRepository petRepository) {
        this.repository = repository;
        this.petRepository = petRepository;
    }

    @RequestMapping("/room/{id}")
    public String connectRoom(OAuth2Authentication authentication,
                              Model model,
                              @PathVariable String id) {
        Long userId = Long.valueOf(new UserController().getUserId(authentication));
        Pet userPet = petRepository.findPetById(userId);

        model.addAttribute("pet", userPet);
        repository.findBattleById(id).addPlayer(userPet);
        return "battle";
    }

    @MessageMapping("/action/{id}")
    @SendTo("/battle/log/{id}")
    public BattleLog log(OAuth2Authentication authentication, String message, @DestinationVariable Long id) {
        String username = new UserController().getUsername(authentication);

        return repository.findBattleById(String.valueOf(id)).setPlayerAction(username, message);
    }

    @MessageMapping("/requestAllBattles")
    @SendTo("/lobby/battles")
    public Iterable<Battle> battlesList() {
        return repository.findAll();
    }
}
