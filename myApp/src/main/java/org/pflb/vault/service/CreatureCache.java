package org.pflb.vault.service;

import org.pflb.vault.model.Creature;
import org.pflb.vault.model.RaceType;

import java.util.List;

public interface CreatureCache {

    void safeCreature(Creature creature);
    Creature getCreatureByName(String name);
    List<Creature> getAllByDamagePerSecondGreaterThan(Integer damagePerSecondLimit);
    List<Creature> getAllByRace(RaceType type);
    List<Creature> getAllByHitPointsGreaterThanAndLevelEquals(int hitPoints, int level);
}
