create sequence client_seq start with 1;

create table client
(
    id bigint not null default nextval('client_seq') primary key,
    name varchar(50)
);
