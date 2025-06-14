-- Insert 5 clients
insert into client (id, name)
values (1, 'Jon Snow');
insert into client (id, name)
values (2, 'Daenerys Targaryen');
insert into client (id, name)
values (3, 'Tyrion Lannister');
insert into client (id, name)
values (4, 'Arya Stark');
insert into client (id, name)
values (5, 'Cersei Lannister');

-- Insert addresses for the clients
insert into address (id, street, client_id)
values (1, 'Winterfell, The North, Westeros', 1);
insert into address (id, street, client_id)
values (2, 'Dragonstone, Westeros', 2);
insert into address (id, street, client_id)
values (3, 'Casterly Rock, Westeros', 3);
insert into address (id, street, client_id)
values (4, 'Winterfell, The North, Westeros', 4);
insert into address (id, street, client_id)
values (5, 'King`s Landing, Westeros', 5);

-- Insert phones for the clients
insert into phone (id, number, client_id)
values (1, '555-0001', 1);
insert into phone (id, number, client_id)
values (2, '555-0002', 2);
insert into phone (id, number, client_id)
values (3, '555-0003', 3);
insert into phone (id, number, client_id)
values (4, '555-0004', 4);
insert into phone (id, number, client_id)
values (5, '555-0005', 5);
insert into phone (id, number, client_id)
values (6, '555-0006', 5);
insert into phone (id, number, client_id)
values (7, '555-0007', 2);
