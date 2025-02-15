-- Insert 5 clients
insert into client (id, name, address_id) values (nextval('client_seq'), 'Jon Snow', nextval('address_seq'));
insert into client (id, name, address_id) values (nextval('client_seq'), 'Daenerys Targaryen', nextval('address_seq'));
insert into client (id, name, address_id) values (nextval('client_seq'), 'Tyrion Lannister', nextval('address_seq'));
insert into client (id, name, address_id) values (nextval('client_seq'), 'Arya Stark', nextval('address_seq'));
insert into client (id, name, address_id) values (nextval('client_seq'), 'Cersei Lannister', nextval('address_seq'));

-- Insert addresses for the clients
insert into address (id, street) values (currval('address_seq') - 4, 'Winterfell, The North, Westeros');
insert into address (id, street) values (currval('address_seq') - 3, 'Dragonstone, Westeros');
insert into address (id, street) values (currval('address_seq') - 2, 'Casterly Rock, Westeros');
insert into address (id, street) values (currval('address_seq') - 1, 'Winterfell, The North, Westeros');
insert into address (id, street) values (currval('address_seq'), 'Red Keep, King`s Landing, Westeros');

-- Insert phones for the clients
insert into phone (id, number, client_id) values (nextval('phone_seq'), '555-0001', currval('client_seq') - 4);
insert into phone (id, number, client_id) values (nextval('phone_seq'), '555-0002', currval('client_seq') - 3);
insert into phone (id, number, client_id) values (nextval('phone_seq'), '555-0003', currval('client_seq') - 2);
insert into phone (id, number, client_id) values (nextval('phone_seq'), '555-0004', currval('client_seq') - 1);
insert into phone (id, number, client_id) values (nextval('phone_seq'), '555-0005', currval('client_seq'));