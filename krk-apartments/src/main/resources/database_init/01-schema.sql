--liquibase formatted sql
--changeset em:1

DROP TABLE IF EXISTS bookings;
create table bookings
(
    ID             UUID not null primary key,
    USER_ID        UUID,
    APARTMENT_ID   UUID,
    PRICE          INTEGER,
    CHECK_IN_DATE  DATE not null,
    CHECK_OUT_DATE DATE not null
);

DROP TABLE IF EXISTS users;
create table users
(
    ID               UUID         not null primary key,
    EMAIL            VARCHAR(255) not null,
    FIRST_NAME       VARCHAR(255) not null,
    LAST_NAME        VARCHAR(255) not null,
    TELEPHONE_NUMBER VARCHAR(255) not null
);

DROP TABLE IF EXISTS apartments;
create table apartments
(
    ID                    UUID             not null primary key,
    APARTMENT_NAME        VARCHAR(255),
    PRICE_FOR_ONE_DAY     DOUBLE PRECISION not null,
    APARTMENT_DESCRIPTION VARCHAR(10000),
    ACTIVE                BOOLEAN          not null,
    BOOKING_URL           VARCHAR(255),
    ADDRESS_ID            UUID
);

DROP TABLE IF EXISTS address;
create table address
(
    ID               UUID    not null primary key,
    CITY             VARCHAR(255),
    STREET_NAME      VARCHAR(255),
    BUILDING_NUMBER  INTEGER not null,
    APARTMENT_NUMBER INTEGER not null,
    POST_CODE        VARCHAR(255),
    COUNTRY          VARCHAR(255)
);
DROP TABLE IF EXISTS admins;
CREATE TABLE admins
(
    UUID       UUID         not null primary key,
    ACTIVE     BOOLEAN      not null,
    EMAIL      VARCHAR(255) not null,
    FIRST_NAME VARCHAR(255) not null,
    LAST_NAME  VARCHAR(255) not null,
    PASSWORD   VARCHAR(255) not null,
    ROLE       VARCHAR(255) not null
);
ALTER TABLE admins
    ADD CONSTRAINT UK_OB8KQYQQGMEFL0ACO34AKDTPE UNIQUE (EMAIL);
alter TABLE bookings
    add CONSTRAINT FKDW0XFNNTHBJ8AFP1IRA6SNDWQ
        foreign key (USER_ID) references users (ID);
alter table bookings
    add CONSTRAINT FKDW0XFNNTHBJ8AFP1IRA6SNDV
        foreign key (APARTMENT_ID) references apartments (ID);
alter table apartments
    add constraint FKDK0XFNNTHBJ8AFP1IRA6SNXTE
        foreign key (ADDRESS_ID) references address (ID);