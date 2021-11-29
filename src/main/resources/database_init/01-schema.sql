--liquibase formatted sql
--changeset em:1
create table USERS
(
    ID       uuid         not null primary key,
    EMAIL      VARCHAR(255) not null,
    FIRST_NAME VARCHAR(255) not null,
    LAST_NAME  VARCHAR(255) not null,
    PASSWORD   VARCHAR(255) not null,
    BOOKING_ID BINARY,
    constraint FKDW0XFNNTHBJ8AFP1IRA6SNDWH
        foreign key (BOOKING_ID) references BOOKING(ID)
);
create table APARTMENTS
(
	ID BINARY not null primary key,
	APARTMENT_NAME VARCHAR(255),
	PRICE_FOR_ONE_DAY DOUBLE not null,
	APARTMENT_DESCRIPTION VARCHAR(10000),
	OCCUPIED BOOLEAN not null,
    ADDRESS_ID BINARY,
    constraint FKDK0XFNNTHBJ8AFP1IRA6SNDTE
        foreign key (ADDRESS_ID) references ADDRESS(ID)
);

create table ADDRESS
(
    ID BINARY not null primary key,
    CITY VARCHAR(255),
    POST_CODE VARCHAR(255),
    COUNTRY VARCHAR(255),
    APARTMENT_ID BINARY,
    constraint FKDK0XFNNTHBJ8AFP1IRA6SNDWQ
        foreign key (APARTMENT_ID) references APARTMENTS(ID)
);



create table BOOKING
(
    ID BINARY not null primary key,
    USER_ID BINARY,
    constraint FKDW0XFNNTHBJ8AFP1IRA6SNDWQ
        foreign key (USER_ID) references USERS(ID),
   APARTMENT_ID BINARY,
    constraint FKDW0XFNNTHBJ8AFP1IRA6SNDWQ
        foreign key (APARTMENT_ID) references APARTMENTS(ID),
    CHECK_IN_DATE DATE not null,
    CHECK_OUT_DATE DATE not null,
    OCCUPIED BOOLEAN not null
);



