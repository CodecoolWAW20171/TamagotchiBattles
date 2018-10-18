package com.codecool.tamagotchi.pet;

import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long>{

    Pet findPetById(Long id);
}
