package com.codecool.tamagotchi.battle;

import org.springframework.data.repository.CrudRepository;

public interface BattleRepository extends CrudRepository<Battle, Long>{

    Battle findBattleById(String id);

}
