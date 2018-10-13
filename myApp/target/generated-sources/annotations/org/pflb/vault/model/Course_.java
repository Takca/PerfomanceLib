package org.pflb.vault.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Course.class)
public abstract class Course_ {

	public static volatile SingularAttribute<Course, Long> numOfDays;
	public static volatile SingularAttribute<Course, Date> dateStart;
	public static volatile SingularAttribute<Course, String> name;
	public static volatile SingularAttribute<Course, Long> id;
	public static volatile SingularAttribute<Course, Date> dateEnd;

	public static final String NUM_OF_DAYS = "numOfDays";
	public static final String DATE_START = "dateStart";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String DATE_END = "dateEnd";

}

