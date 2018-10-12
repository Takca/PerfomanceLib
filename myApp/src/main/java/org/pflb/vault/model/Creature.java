package org.pflb.vault.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "CREATURE")
public class Creature implements Serializable {

    @Id
    @Column (name = "id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "level")
    private int level;

    @Enumerated
    @Column (name = "race")
    private RaceType race;

    @Column (name = "dps")
    private int damagePerSecond;

    @Column (name = "hp")
    private int hitPoints;

    @ManyToOne
    private User creatureCreator;

}
