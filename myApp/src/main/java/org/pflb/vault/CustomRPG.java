package org.pflb.vault;

import lombok.extern.log4j.Log4j;
import org.pflb.vault.model.Person;
import org.pflb.vault.model.PersonDragon;
import org.pflb.vault.model.PersonElf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.IntStream;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
@EnableJpaRepositories
@PropertySource(value = {"classpath:env/${env}/web.properties",
        "classpath:env/${env}/db.properties"})
public class CustomRPG {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(CustomRPG.class, args);

        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                if (person1.getLevel() > person2.getLevel()) {
                    return 1;
                } else if (person1.getLevel() < person2.getLevel()) {
                    return -1;
                }
                if (person1.equals(person2)) {
                    return 0;
                }
                return 1;
            }
        };

        TreeMap<Person, String> personsDictionary = new TreeMap<>(comparator);

        for (int i = 2; i < 100; i++) {
            Person elfGenerated = new PersonElf(i, "Elf" + i);
            personsDictionary.put(elfGenerated, "" + Math.random());
        }

        for (int i = 2; i < 100; i++) {
            Person dragonGenerated = new PersonDragon(i, "Dragon" + i);
            personsDictionary.put(dragonGenerated, "" + Math.random());
        }


        int sum = personsDictionary.keySet()
                .stream()
                .filter(z -> z.getName().contains("2"))
                .flatMapToInt(z -> IntStream.of(z.getDamagePerSecond()))
                .sum();

        System.out.println(sum);

    }

}
