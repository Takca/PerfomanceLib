CREATE TABLE CREATURE(
  id                        BIGINT         NOT NULL,

  hp                        NUMBER         NOT NULL,
  dps                       NUMBER         NOT NULL,
  name                      VARCHAR(255)   NOT NULL,
  race                      VARCHAR(255)   NOT NULL,
  level                     NUMBER         NOT NULL,

);

ALTER TABLE CREATURE
  ADD CONSTRAINT CREATURE_ID_PK PRIMARY KEY (id);

CREATE TABLE WEAPON (
id NUMBER NOT NULL,
quality NUMBER NOT NULL,
creature_id number
);

ALTER TABLE WEAPON
ADD CONSTRAINT WEAPON_ID_PK PRIMARY KEY (id);

insert into WEAPON values (1, 1, 1);
insert into WEAPON values (2, 2, 2);
insert into WEAPON values (3, 3, 3);
insert into WEAPON values (4, 4, 4);