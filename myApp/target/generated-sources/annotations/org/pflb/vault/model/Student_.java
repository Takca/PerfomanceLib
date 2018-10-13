package org.pflb.vault.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ {

	public static volatile SingularAttribute<Student, String> phone;
	public static volatile SingularAttribute<Student, String> name;
	public static volatile SingularAttribute<Student, Long> id;
	public static volatile SingularAttribute<Student, String> email;

	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

