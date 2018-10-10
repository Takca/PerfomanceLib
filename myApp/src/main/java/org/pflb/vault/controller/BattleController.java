package org.pflb.vault.controller;

import org.pflb.vault.CustomRpgExeption;
import org.pflb.vault.model.Creature;
import org.pflb.vault.model.RaceType;
import org.pflb.vault.service.CreatureCache;
import org.pflb.vault.service.CreatureManagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class BattleController {

    @Qualifier("persistent")
    @Autowired
    private CreatureCache storage;

    @Autowired
    private CreatureManagingService creatureManagingService;

    @GetMapping("creature/{name}/{level}/{type}")
    public String createCreature(@PathVariable String name, @PathVariable int level, @PathVariable RaceType type) {
        Creature creature = creatureManagingService.createCreature(level, name, type);
        storage.safeCreature(creature);
        return "Привет " + creature.toString();
    }

    @GetMapping("creature/get-all-with-damage-more-then/{dps}")
    public List<Creature> getCreature(@PathVariable Integer dps) {
        return storage.getAllByDamagePerSecondGreaterThan(dps);
    }

    @GetMapping("creaturses/get-all-by-race/{type}")
    public  List<Creature> getCreature(@PathVariable RaceType type) {
        return storage.getAllByRace(type);
    }

    @GetMapping("creatures/get-all-by/{hitPoints}/{level}")
    public List<Creature> getCreature(@PathVariable int hitPoints, @PathVariable int level) {
        return storage.getAllByHitPointsGreaterThanAndLevelEquals(hitPoints, level);
    }

    @GetMapping("creature/get/{name}")
    public Creature getCreature(@PathVariable String name) {
        return storage.getCreatureByName(name);
    }


    @GetMapping("hello/{value}")
    public String helloWorldMethod(@PathVariable String value) {
        System.out.println(value);
        return "Привет " + value;
    }

    @GetMapping("do-impossible/{number}")
    public int doImposible(@PathVariable String number) {

        int i;

        try {
            int numberInteger = Integer.parseInt(number);
            i = 100 / numberInteger;
        } catch (ArithmeticException e) {
            System.out.println("Зарегистрированна нормальная активность");
            System.out.println(e.getMessage());
            throw new CustomRpgExeption("НЕ ПЕРЕДАВАЙ НОЛЬ");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ArithmeticException("НЕ ВЫШЛО");
        }

        return i;
    }
}
