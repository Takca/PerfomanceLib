package org.pflb.vault.model;

import lombok.extern.slf4j.Slf4j;
import org.pflb.vault.model.config.CreatureConfiuration;
import org.pflb.vault.model.config.CreatureStats;

@Slf4j
public enum RaceType {

    ELF,
    DRAGON,
    HUMAN,
    DWARF;

    public static CreatureStats getStatsByType(RaceType type) {
        switch (type) {
            case ELF:
                return CreatureConfiuration.elfStats;
            case DRAGON:
                return CreatureConfiuration.dragonStats;
            case DWARF:
                return CreatureConfiuration.dwarfStats;
            case HUMAN:
                return CreatureConfiuration.humanStats;
        }
        log.warn("Для типа " + type.name() + " не предоставлена конфигурация");
        return null;
    }

}
