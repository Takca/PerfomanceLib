package org.pflb.vault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;

@Configuration
@ComponentScan
@EnableCaching
@EnableAutoConfiguration
@EnableScheduling
@EnableJpaRepositories
@PropertySource(value = {"classpath:env/${env}/web.properties",
        "classpath:env/${env}/db.properties"})
public class CustomRPG {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("rofl");
    }

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(CustomRPG.class, args);
/*
            Comparator<CreatureManagingService> comparator = new Comparator<CreatureManagingService>() {
            @Override
            public int compare(CreatureManagingService person1, CreatureManagingService person2) {
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

        TreeMap<CreatureManagingService, String> personsDictionary = new TreeMap<>(comparator);

        for (int i = 2; i < 100; i++) {
            CreatureManagingService elfGenerated = new CreatureElf(i, "Elf" + i);
            personsDictionary.put(elfGenerated, "" + Math.random());
        }

        for (int i = 2; i < 100; i++) {
            CreatureManagingService dragonGenerated = new CreatureDragon(i, "Dragon" + i);
            personsDictionary.put(dragonGenerated, "" + Math.random());
        }


        int sum = personsDictionary.keySet()
                .stream()
                .filter(z -> z.getName().contains("2"))
                .flatMapToInt(z -> IntStream.of(z.getDamagePerSecond()))
                .sum();

        System.out.println(sum);
*/
    }

}

/*
Первичный ключ -

Sneil - переменные пишуся через нижнее подчеркивание

Версиронирование базы данных
FlyWayDB, Apache +in

insert into PERSON values (4, 100, 'imya', 7, 1);
select * from PERSON where level > 2 and hitponts > 1


ALTER TABLE PERSON CHANGE HITPONTS HITPOINTS NUMBER - ПЕРЕИМЕНОВАТЬ

Агрегация - работа с разу с нес

select * from PERSON, WEAPON where  WEAPON.person_id = PERSON.id;

select * from PERSON where id = (select PERSON_id from WEAPON where quality in (select max(quality) from WEAPON));

select * from PERSON inner join WEAPON on WEAPON.person_id = PERSON.id; - это типа правильно
inner join - когда есть все данные (например нет челиков без оружия)
left join - когда не у всех есть оружие, но чтобы их тоже вывело
right
outer

left и right определяют главенство одной из таблиц
inner - полное пересечение, где подходит условие после where


distinct - фильтр после select, который убирает повторения

Валидация

Check uncheck исключения

Если в методе используется наследник Runtime исключения, то его можно явно де декларировать в начале

Spring потрошитель Евгений Борисов

Анотация Scope

maven central
mvnrepository.com

hibernate ORM

Анонимный класс

Аспектно ориентированное программирование

afterMigrate - плгин, который выполняется при старте
 */