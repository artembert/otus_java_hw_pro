alter table client
    add address_id bigint;

create sequence address_seq start with 1;

create table address
(
    id bigint not null default nextval('address_seq') primary key,
    street varchar(300) not null
);
