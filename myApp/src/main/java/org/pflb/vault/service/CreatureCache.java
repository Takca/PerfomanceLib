package org.pflb.vault.service;

import org.pflb.vault.model.Creature;

public interface CreatureCache {

    void safeCreature(Creature creature);
    Creature getCreatureByName(String name);

}
