package org.pflb.vault.service;

import com.google.common.collect.Maps;
import org.pflb.vault.model.Creature;
import org.pflb.vault.model.RaceType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CreatureInMemmoryCache implements CreatureCache {
    Map<String, Creature> cretureStorage = Maps.newHashMap();

    public void safeCreature(Creature creature) {
        cretureStorage.put(creature.getName(), creature);
    }

    public Creature getCreatureByName(String cretureName) {
        return cretureStorage.get(cretureName);
    }

    @Override
    public List<Creature> getAllByDamagePerSecondGreaterThan(Integer damagePerSecondLimit) {
        return cretureStorage.values()
                .stream()
                .filter(z -> z.getDamagePerSecond() > damagePerSecondLimit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Creature> getAllByRace(RaceType raceType) {
        return cretureStorage.values()
                .stream()
                .filter(z -> z.getRace() == raceType)
                .collect(Collectors.toList());
    }

    @Override
    public List<Creature> getAllByHitPointsGreaterThanAndLevelEquals(int hitPoints, int level) {
        return cretureStorage.values()
                .stream()
                .filter(z -> z.getHitPoints() > hitPoints && z.getLevel() == level)
                .collect(Collectors.toList());
    }
}
