package org.pflb.vault.service;

import org.pflb.vault.model.Creature;
import org.pflb.vault.model.RaceType;
import org.pflb.vault.repository.CreatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("persistent")
public class CreaturePersistentStorageImpl implements CreatureCache {

    @Autowired
    CreatureRepository creatureRepository;

    @Override
    public void safeCreature(Creature creature) {
        creatureRepository.save(creature);
    }

    @Override
    public Creature getCreatureByName(String name) {
        return creatureRepository.getCreatureByName(name);
    }

    @Override
    public List<Creature> getAllByDamagePerSecondGreaterThan(Integer damagePerSecondLimit) {
        return creatureRepository.getAllByDamagePerSecondGreaterThan(damagePerSecondLimit);
    }

    @Override
    public List<Creature> getAllByRace(RaceType type) {
        return creatureRepository.getAllByRace(type);
    }

    @Override
    public List<Creature> getAllByHitPointsGreaterThanAndLevelEquals(int hitPoints, int level) {
        return creatureRepository.getAllByHitPointsGreaterThanAndLevelEquals(hitPoints, level);
    }

}
