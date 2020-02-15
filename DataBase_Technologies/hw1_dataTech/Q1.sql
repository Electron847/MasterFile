CREATE table customer (
    CustomerID      number(3),
    name            varchar(25),
    Address         varchar(50)
);

CREATE TABLE fullorder (
    OrderID         number(5),
    OrderDate       varchar(10),
    CustomerID      number(10)
);

create table request (
    OrderID         number(5),
    ProductID       number(2),
    Quantity        number(4)
    
    CONSTRAINT ch CHECK (Quantity >= 1),
    CONSTRAINT ch3 CHECK (Quantity <= 100)
);

create table product (
    ProductID       number(2),
    Description     varchar(28),
    Finish          varchar(10),
    Price           float(4),
    
    CONSTRAINT ch1 CHECK (1000 > Price),
    CONSTRAINT ch2 CHECK (Price > 0)
);

insert INTO customer VALUES(2, 'CASUAL FURNITURE', 'PLANO, TX');
insert INTO customer VALUES(6, 'MOUNTAIN GALLERY', 'BOULDER, CO');

insert INTO fullorder VALUES(1006, '24-MAR-10', 2);
insert INTO fullorder VALUES(1007, '25-MAR-10', 6);
insert INTO fullorder VALUES(1008, '25-MAR-10', 6);
insert INTO fullorder VALUES(1009, '26-MAR-10', 2);

insert into product values (10, 'WRITING DESK', 'OAK', 425);    
insert into product values (30, 'DINING TABLE', 'ASH', 600);
insert into product values (40, 'ENTERTAINMENT CENTER', 'MAPLE', 650);
insert into product values (70, 'CHILDRENS DRESSER', 'PINE', 300);

insert into request values (1006, 10, 4);
insert into request values (1006, 30, 2);
insert into request values (1006, 40, 1);
insert into request values (1007, 40, 3);
insert into request values (1007, 70, 2);
insert into request values (1008, 70, 1);
insert into request values (1009, 10, 2);
insert into request values (1009, 40, 1);

SELECT * FROM customer;
SELECT * FROM fullorder;
SELECT * FROM product;
SELECT * FROM request;

drop table product;
drop table customer;
drop table fullorder;
drop table request;
