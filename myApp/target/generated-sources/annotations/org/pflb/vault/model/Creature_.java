package org.pflb.vault.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Creature.class)
public abstract class Creature_ {

	public static volatile SingularAttribute<Creature, RaceType> race;
	public static volatile SingularAttribute<Creature, Integer> level;
	public static volatile SingularAttribute<Creature, Integer> damagePerSecond;
	public static volatile SingularAttribute<Creature, String> name;
	public static volatile SingularAttribute<Creature, Integer> hitPoints;
	public static volatile SingularAttribute<Creature, Long> id;

	public static final String RACE = "race";
	public static final String LEVEL = "level";
	public static final String DAMAGE_PER_SECOND = "damagePerSecond";
	public static final String NAME = "name";
	public static final String HIT_POINTS = "hitPoints";
	public static final String ID = "id";

}

