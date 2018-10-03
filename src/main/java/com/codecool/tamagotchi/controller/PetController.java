package com.codecool.tamagotchi.controller;

import com.codecool.tamagotchi.model.tamagotchi.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetController {
    @GetMapping("/create_gotchi")
    public String petForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "create_gotchi";
    }

    @PostMapping("/create_gotchi")
    public String greetingSubmit(@ModelAttribute Pet pet) {
        return "result";
    }

}
