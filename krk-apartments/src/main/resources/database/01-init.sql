create table address
(
    id uuid not null
        constraint address_pkey
            primary key,
    apartment_number integer not null,
    building_number integer not null,
    city varchar(255),
    country varchar(255),
    post_code varchar(255),
    street_name varchar(255)
);

create table admin
(
    uuid uuid not null
        constraint admin_pkey
            primary key,
    active boolean not null,
    email varchar(255)
        constraint uk_c0r9atamxvbhjjvy5j8da1kam
            unique,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null
);

create table apartment
(
    id uuid not null
        constraint apartment_pkey
            primary key,
    active boolean not null,
    apartment_description varchar(255),
    apartment_name varchar(255),
    booking_url varchar(255),
    price_for_one_day integer not null,
    address_id uuid
        constraint fkewm9sknyj3o4ss02td055orh0
            references address
);

create table transaction
(
    session_id varchar(255) not null
        constraint transaction_pkey
            primary key,
    amount integer,
    currency varchar(255),
    merchant_id integer,
    order_id integer,
    pos_id integer,
    status varchar(255)
);

create table "user"
(
    id uuid not null
        constraint user_pkey
            primary key,
    email varchar(255)
        constraint uk_ob8kqyqqgmefl0aco34akdtpe
            unique,
    first_name varchar(255),
    last_name varchar(255),
    telephone_number varchar(255)
);

create table booking
(
    id uuid not null
        constraint booking_pkey
            primary key,
    check_in_date date,
    check_out_date date,
    payment_status varchar(255),
    price integer not null,
    apartment_id uuid
        constraint fktf8mt0474jyw0it7iabmsh6t3
            references apartment,
    user_id uuid
        constraint fk21cnrmtlo37b8bjon7m6d543w
            references "user"
);