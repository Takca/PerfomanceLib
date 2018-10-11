package org.pflb.vault.controller;

import org.pflb.vault.CustomRpgExeption;
import org.pflb.vault.model.Course;
import org.pflb.vault.model.Creature;
import org.pflb.vault.model.RaceType;
import org.pflb.vault.model.Student;
import org.pflb.vault.service.CoursePersistentStorage;
import org.pflb.vault.service.CreatureCache;
import org.pflb.vault.service.CreatureManagingService;
import org.pflb.vault.service.StudentPersistentStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    private StudentPersistentStorage studentStorage;

    @Autowired
    private CreatureManagingService creatureManagingService;

    @PostMapping("/courses/")
    public String createCourse(@RequestBody Course course) {
        coursePersistentStorage.saveCourse(course);
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
    public Course getCourseById(@PathVariable Long id) {
        return coursePersistentStorage.getCourseById(id);
    }

    @PostMapping("students/")
    public String createStudent(@RequestBody Student student) {
        studentStorage.saveStudent(student);
        return "Привет, " + student.toString();
    }

    @GetMapping("students/")
    public List<Student> getAllStudents() {
        return studentStorage.getAll();
    }

    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentStorage.getStudentById(id);
    }

    @DeleteMapping("students/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentStorage.deleteStudentById(id);
    }


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
    public int doImpossible(@PathVariable String number) {

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
