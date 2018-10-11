package org.pflb.vault.controller;

import org.pflb.vault.CustomRpgExeption;
import org.pflb.vault.model.Course;
import org.pflb.vault.model.Creature;
import org.pflb.vault.model.RaceType;
import org.pflb.vault.service.CoursePersistentStorage;
import org.pflb.vault.service.CreatureCache;
import org.pflb.vault.service.CreatureManagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class BattleController {

    @Autowired
    @Qualifier("persistent")
    private CreatureCache storage;

    @Autowired
    private CoursePersistentStorage coursePersistentStorage;

    @Autowired
    private CreatureManagingService creatureManagingService;

    @GetMapping("creature/{name}/{level}/{type}")
    public String createCreature(@PathVariable String name, @PathVariable int level, @PathVariable RaceType type) {
        Creature creature = creatureManagingService.createCreature(level, name, type);
        storage.safeCreature(creature);
        return "Привет " + creature.toString();
    }

    @GetMapping("/{name}/{dateStart}/{dateEnd}/{numOfDays}")
    public String createCourse(@PathVariable String name, @PathVariable Date dateStart, @PathVariable Date dateEnd, @PathVariable Long numOfDays) {
        Course course = new Course();
        course.setName(name);
        course.setDateStart(dateStart);
        course.setDateEnd(dateEnd);
        course.setNumOfDays(numOfDays);
        coursePersistentStorage.safeCourse(course);
        return course.toString();
    }

    @GetMapping("/course/all")
    public String getAllCourses() {
        String httpResponse = "";
        for (Course i : coursePersistentStorage.getAll()) {
            httpResponse += "<a href=\"/api/course/" + i.getId() + "\">" + i.getName() + "</a><br />";
        }
        return httpResponse;
    }

    @GetMapping("/course/{id}")
    public String getCourseById(@PathVariable Long id) {
        return coursePersistentStorage.getCourseById(id).toString();
    }

    @GetMapping("creature/get-all-with-damage-more-then/{dps}")
    public List<Creature> getCreature(@PathVariable Integer dps) {
        return storage.getAllByDamagePerSecondGreaterThan(dps);
    }

    @GetMapping("creaturses/get-all-by-race/{type}")
    public List<Creature> getCreature(@PathVariable RaceType type) {
        return storage.getAllByRace(type);
    }

    @GetMapping("creatures/get-all-by/{hitPoints}/{level}")
    public List<Creature> getCreature(@PathVariable int hitPoints, @PathVariable int level) {
        return storage.getAllByHitPointsGreaterThanAndLevelEquals(hitPoints, level);
    }

    @Secured("ROLE_COMMON_USER")
    @GetMapping("creatures/get/{name}")
    public Creature getCreature(@PathVariable String name) {
        return storage.getCreatureByName(name);
    }

    @GetMapping("creature/get/{name}")
    public Creature getUser(@PathVariable String name) {
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
