-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence account_SEQ start with 1 increment by 1;

create table account
(
    id       bigint      not null primary key,
    email    varchar(50) not null,
    password varchar(50) not null
);


-- Insert 5 accounts
insert into account (id, email, password)
values (nextval('account_SEQ'), 'account1', 11111);
insert into account (id, email, password)
values (nextval('account_SEQ'), 'account2', 11111);
insert into account (id, email, password)
values (nextval('account_SEQ'), 'account3', 11111);
insert into account (id, email, password)
values (nextval('account_SEQ'), 'account4', 11111);
insert into account (id, email, password)
values (nextval('account_SEQ'), 'account5', 11111);