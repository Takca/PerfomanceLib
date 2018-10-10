package org.pflb.vault.service;

import com.google.common.collect.Maps;
import org.pflb.vault.model.Creature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CreatureInMemmoryCache implements CreatureCache {
    Map<String, Creature> cretureStorage = Maps.newHashMap();

    public void safeCreature(Creature creature) {
        cretureStorage.put(creature.getName(), creature);
    }

    public Creature getCreatureByName(String cretureName) {
        return cretureStorage.get(cretureName);
    }
}
