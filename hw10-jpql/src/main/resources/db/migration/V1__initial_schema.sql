-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

 */

-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence hibernate_sequence start with 1 increment by 1;
create sequence address_SEQ start with 1 increment by 1;
create sequence client_seq start with 1 increment by 1;

create table address
(
    id     bigserial not null primary key,
    street varchar(50)
);


create table client
(
    id         bigserial not null primary key,
    name       varchar(50),
    address_id bigint references address (id)
);
