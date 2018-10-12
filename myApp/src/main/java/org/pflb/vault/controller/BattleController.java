package org.pflb.vault.controller;

import org.pflb.vault.CustomRpgExeption;
import org.pflb.vault.model.*;
import org.pflb.vault.repository.UserRepository;
import org.pflb.vault.security.RPGUserDetails;
import org.pflb.vault.service.CoursePersistentStorage;
import org.pflb.vault.service.CreatureCache;
import org.pflb.vault.service.CreatureManagingService;
import org.pflb.vault.service.StudentPersistentStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class BattleController {

    @Autowired
    private UserRepository userRepository;

    @Qualifier("persistent")
    @Autowired
    private CreatureCache storage;

    @Autowired
    private CoursePersistentStorage coursePersistentStorage;

    @Autowired
    private StudentPersistentStorage studentStorage;

    @Autowired
    private CreatureManagingService creatureManagingService;

    @Secured("ROLE_COMMON_USER")
    @GetMapping("/index")
    public String getMetrics() {
        String httpResponse = "";
        Date sqlCurrentDate = new Date(Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow")).getTimeInMillis());

        List<Course> currentCourses = coursePersistentStorage.getAll()
                .stream()
                .filter(z -> z.getDateStart().before(sqlCurrentDate) && z.getDateEnd().after(sqlCurrentDate))
                .collect(Collectors.toList());

        List<Course> comingCourses = coursePersistentStorage.getAll()
                .stream()
                .filter(z -> z.getDateStart().after(sqlCurrentDate))
                .collect(Collectors.toList());

        List<Course> pastCourses = coursePersistentStorage.getAll()
                .stream()
                .filter(z -> z.getDateEnd().before(sqlCurrentDate))
                .collect(Collectors.toList());

        httpResponse += " " + sqlCurrentDate + "<br />";
        httpResponse += "Текущие курсы (" + currentCourses.size() + "): <br /> " + currentCourses.toString() + ": <br /> ";
        httpResponse += "Предстоящие курсы (" + comingCourses.size() + "): <br /> " + comingCourses.toString() + ": <br /> ";
        httpResponse += "Прощедщие курсы (" + pastCourses.size() + ":) <br /> " + pastCourses.toString() + ": <br /> ";
        httpResponse += "Количество студентов: " + studentStorage.count();

        return httpResponse;
    }

    @Secured("ROLE_COMMON_USER")
    @PostMapping("/courses")
    public String createCourse(@RequestBody Course course) {
        coursePersistentStorage.saveCourse(course);
        return course.toString();
    }

    @Secured("ROLE_COMMON_USER")
    @GetMapping("/course/all")
    public String getAllCourses() {
        String httpResponse = "";
        for (Course i : coursePersistentStorage.getAll()) {
            //httpResponse += "<a href=\"/api/course/" + i.getId() + "\">" + i.getName() + "</a><br />";
            httpResponse += i.toString();
        }
        return httpResponse;
    }

    @Secured("ROLE_COMMON_USER")
    @GetMapping("/course/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return coursePersistentStorage.getCourseById(id);
    }

    @Secured("ROLE_COMMON_USER")
    @PostMapping("students/")
    public String createStudent(@RequestBody Student student) {
        studentStorage.saveStudent(student);
        return "Привет, " + student.toString();
    }

    @Secured("ROLE_COMMON_USER")
    @GetMapping("students/")
    public List<Student> getAllStudents() {
        return studentStorage.getAll();
    }

    @Secured("ROLE_COMMON_USER")
    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        User user = getLogInUserFromContext();
        Student studentById = studentStorage.getStudentById(id);
        boolean loginEquals = studentById.getName().equals(user.getLogin());
        if (loginEquals) {
            return new ResponseEntity<>(studentById, HttpStatus.ACCEPTED);
        }
        throw new CustomRpgExeption("roflanEbalo");
        //return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
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

    // getContext getAuth  getPrincipal -> user -> perenosim v user details
    // Для регистриции нужно сделать страницу с формой, а дальше просто вызываем нужную функцию,
    // в котороую передаём имя, пароль, пароль еще раз и т.д.
    // Добавить конфигурацию спринг мвц, там мы сможем возвращать ссылки на страницы
    // В Секьюрите контаин посмотрнть детаил
    // sequrity contect util spring
    // securitycontextholder

    @Secured("ROLE_COMMON_USER")
    @GetMapping("creatures/get/{name}")
    public ResponseEntity<Creature> getCreature(@PathVariable String name) {
        User user = getLogInUserFromContext();
        Creature creatureByName = storage.getCreatureByName(name);
        boolean loginEquals = creatureByName.getCreatureCreator().getLogin().equals(user.getLogin());
        if (loginEquals) {
            return new ResponseEntity<>(creatureByName, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    public User getLogInUserFromContext() {
        RPGUserDetails principal = (RPGUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.getByLogin(principal.getUsername());
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
