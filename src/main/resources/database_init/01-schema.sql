--liquibase formatted sql
--changeset em:1
DROP TABLE IF EXISTS BOOKINGS;
create table BOOKINGS
(
    ID BINARY not null primary key,
    USER_ID BINARY,
    APARTMENT_ID BINARY,
    CHECK_IN_DATE DATE not null,
    CHECK_OUT_DATE DATE not null
   // OCCUPIED BOOLEAN not null
);

DROP TABLE IF EXISTS USERS;
create table USERS
(
    ID       BINARY         not null primary key,
    EMAIL      VARCHAR(255) not null,
    FIRST_NAME VARCHAR(255) not null,
    LAST_NAME  VARCHAR(255) not null
);

DROP TABLE IF EXISTS APARTMENTS;
create table APARTMENTS
(
	ID BINARY not null primary key,
	APARTMENT_NAME VARCHAR(255),
	PRICE_FOR_ONE_DAY DOUBLE not null,
	APARTMENT_DESCRIPTION VARCHAR(10000),
	//OCCUPIED BOOLEAN not null,
    ADDRESS_ID BINARY
);

DROP TABLE IF EXISTS ADDRESS;
create table ADDRESS
(
    ID BINARY not null primary key,
    CITY VARCHAR(255),
    POST_CODE VARCHAR(255),
    COUNTRY VARCHAR(255)
);

DROP TABLE IF EXISTS APARTMENT_IS_OCCUPIED;
create table APARTMENT_IS_OCCUPIED
(
    APARTMENT_ID BINARY not null,
    IS_OCCUPIED BOOLEAN not null,
    DATE DATE not null
);

alter TABLE BOOKINGS
    add CONSTRAINT  FKDW0XFNNTHBJ8AFP1IRA6SNDWQ
        foreign key (USER_ID) references USERS(ID);

alter table BOOKINGS
    add CONSTRAINT  FKDW0XFNNTHBJ8AFP1IRA6SNDV
        foreign key (APARTMENT_ID) references APARTMENTS(ID);

alter table APARTMENTS
    add constraint FKDK0XFNNTHBJ8AFP1IRA6SNXTE
    foreign key (ADDRESS_ID) references ADDRESS(ID);

/*
alter table APARTMENT_IS_OCCUPIED
    add constraint FKDK0XFNNTHBJ8AFP1IRA6SHWDP
    foreign key (APARTMENT_ID) references BOOKINGS(APARTMENT_ID);*/
