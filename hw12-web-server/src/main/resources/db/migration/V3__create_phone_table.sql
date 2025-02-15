-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence phone_SEQ start with 1 increment by 1;

create table phone
(
    id        bigint      not null primary key,
    number    varchar(50) not null,
    client_id bigint references client
);
