package org.pflb.vault.model;

public class CreatureDragon extends Creature {

    static final int INITIAL_DPS = 100;
    static final int INITIAL_HP = 1000;

    static final int DPS_PER_LVL = 1000;
    static final int HP_PER_LVL = -10;

    public CreatureDragon(int level, String name) {
        super(level, name);
        this.setRace(RaceType.DRAGON);
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
