package org.pflb.vault.model;

public class PersonElf extends Person {

    static final int INITIAL_DPS = 10;
    static final int INITIAL_HP = 100;

    static final int DPS_PER_LVL = 1;
    static final int HP_PER_LVL = 10;


    public PersonElf(int level, String name) {
        super(level, name);
        this.setRace(RaceType.ELF);
    }

    @Override
    protected int getInitialDPS() {
        return INITIAL_DPS;
    }

    @Override
    protected int getDPS_PER_LEVEL() {
        return DPS_PER_LVL;
    }

    @Override
    protected int getInitialHP() {
        return INITIAL_HP;
    }

    @Override
    protected int getHP_PER_LEVEL() {
        return HP_PER_LVL;
    }

}
