package org.pflb.vault;

import org.pflb.vault.model.Creature;
import org.pflb.vault.model.CreatureDragon;
import org.pflb.vault.model.CreatureElf;
import org.pflb.vault.model.RaceType;
import org.pflb.vault.service.CreatureCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class BattleController {

    @Autowired
    private CreatureCache storage;

    @GetMapping("creature/{name}/{level}/{type}")
    public String createCreature(@PathVariable String name, @PathVariable int level, @PathVariable RaceType type) {
        Creature creature;
        if (type == RaceType.ELF) {
            creature = new CreatureElf(level, name);
        } else if (type == RaceType.DRAGON) {
            creature = new CreatureDragon(level, name);
        } else {
            return "Нет такого типа существ";
        }
        storage.safeCreature(creature);
        return "Привет " + creature.toString();
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

    @PostMapping("creature/import/elf")
    public String createCreature(CreatureElf creatureElf) {
        if (creatureElf.getRace() != RaceType.ELF) {
            return "Ты кого пытаешся обмануть?";
        }
        return "Привет " + creatureElf.toString();
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
