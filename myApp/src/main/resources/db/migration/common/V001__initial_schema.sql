CREATE TABLE PERSON(
  id                            NUMBER         NOT NULL,
  hitpoints                 NUMBER         NOT NULL,
  race                        VARCHAR(255) NOT NULL,
  level                       NUMBER         NOT NULL,
  is_alive                 NUMBER(1,0) NOT NULL
);

ALTER TABLE PERSON
  ADD CONSTRAINT PERSON_ID_PK PRIMARY KEY (id);

CREATE TABLE WEAPON (
id NUMBER NOT NULL,
quality NUMBER NOT NULL,
person_id number
);

ALTER TABLE WEAPON
ADD CONSTRAINT WEAPON_ID_PK PRIMARY KEY (id);

insert into WEAPON values (1, 1, 1);
insert into WEAPON values (2, 2, 2);
insert into WEAPON values (3, 3, 3);
insert into WEAPON values (4, 4, 4);