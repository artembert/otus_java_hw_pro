create sequence phone_seq start with 1;

create table phone
(
    id bigint not null default nextval('phone_seq') primary key,
    number    varchar(50) not null,
    client_id bigint references client
);
