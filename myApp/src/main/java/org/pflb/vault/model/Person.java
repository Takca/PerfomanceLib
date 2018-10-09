package org.pflb.vault.model;

import java.util.Objects;

public abstract class Person {

    private static int creaturesCount;

    private int hitPoints;
    private RaceType race;
    private int damagePerSecond;
    private String name;
    private int level;

    public boolean isNotDeadYet = true;

    public Person(int level, String name) {
        creaturesCount++;
        this.setLevel(level);
        this.setName(name);
        init(level);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public RaceType getRace() {
        return race;
    }

    public int getDamagePerSecond() {
        return damagePerSecond;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public static int getCreaturesCount() {
        return creaturesCount;
    }

    protected void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    protected void setRace(RaceType race) {
        this.race = race;
    }

    protected void setDamagePerSecond(int damagePerSecond) {
        this.damagePerSecond = damagePerSecond;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setLevel(int level) {
        this.level = level;
    }

    protected abstract int getInitialDPS();

    protected abstract int getDPS_PER_LEVEL();

    protected abstract int getInitialHP();

    protected abstract int getHP_PER_LEVEL();

    protected void init(int level) {
        this.setDamagePerSecond(getInitialDPS() + level * getDPS_PER_LEVEL());
        this.setHitPoints(getInitialHP() + level * getHP_PER_LEVEL());
    }


    @Override
    public String toString() {
        return "Person{" +
                "hitPoints=" + hitPoints +
                ", race=" + race +
                ", damagePerSecond=" + damagePerSecond +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }




    public boolean isDead() {
        if (!isNotDeadYet) {
            return true;
        }
        if (hitPoints > 0) {
            return false;
        }
        isNotDeadYet = false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return hitPoints == person.hitPoints &&
                damagePerSecond == person.damagePerSecond &&
                level == person.level &&
                isNotDeadYet == person.isNotDeadYet &&
                race == person.race &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hitPoints, race, damagePerSecond, name, level, isNotDeadYet);
    }
}
