create table product_group
(
    id              int primary key auto_increment,
    name            varchar(30) not null,
    description     varchar (255) not null,
    unique unique_name (name)
);

create table product
(
    id              int primary key auto_increment,
    name            varchar(30) not null,
    description     varchar (255) not null,
    price           float not null,
    producer        varchar (30) not null,
    product_group   int not null,
    unique unique_name (name),
    constraint fk_product_to_group foreign key (product_group) references product_group(id) on delete cascade
);

create table warehouse
(
    id              int primary key auto_increment,
    name            varchar(30) not null,
    address         varchar(100) not null,
    unique unique_name (name),
    unique unique_address (address)
);

create table warehouse_product
(
    product_id      int not null,
    warehouse_id    int not null,
    quantity        int not null,
    constraint fk_wp_to_product foreign key (product_id) references product(id),
    constraint fk_wp_to_warehouse foreign key (warehouse_id) references warehouse(id),
    constraint pk_warehouse_product primary key (product_id,warehouse_id)
);

create table `order`
(
    id              int primary key auto_increment,
    client          varchar(30) not null,
    status          tinyint not null
);

create table order_to_warehouse_to_product
(
    product_id              int not null,
    warehouse_id            int not null,
    order_id                int not null,
    amount                  int not null,
    constraint fk_owp_to_product foreign key (product_id) references product(id),
    constraint fk_owp_to_warehouse foreign key (warehouse_id) references warehouse(id),
    constraint fk_owp_to_order foreign key (order_id) references `order`(id) on delete cascade,
    constraint pk_order_to_warehouse_to_product primary key (product_id,warehouse_id, order_id)
);

create table booking
(
    id                      int primary key auto_increment,
    booking_time            datetime not null default CURRENT_TIMESTAMP,
    due_time                datetime not null
);

create table booking_to_warehouse_to_product
(
    product_id              int not null,
    warehouse_id            int not null,
    booking_id              int not null,
    amount                  int not null,
    constraint fk_bwp_to_product foreign key (product_id) references product(id),
    constraint fk_bwp_to_warehouse foreign key (warehouse_id) references warehouse(id),
    constraint fk_bwp_to_booking foreign key (booking_id) references booking(id) on delete cascade,
    constraint pk_booking_to_warehouse_to_product primary key (product_id,warehouse_id, booking_id)
);

INSERT INTO `product_group` (`id`, `name`, `description`) VALUES ('1', 'group1', 'descr1');
INSERT INTO `product_group` (`id`, `name`, `description`) VALUES ('2', 'group2', 'descr2');
INSERT INTO `product_group` (`id`, `name`, `description`) VALUES ('3', 'group3', 'descr3');


INSERT INTO `product` (`id`, `name`, `description`, `price`, `producer`, `product_group`) VALUES (1, 'product1', 'descr1', 10.4, 'producer1', 1);
INSERT INTO `product` (`id`, `name`, `description`, `price`, `producer`, `product_group`) VALUES (2, 'product2', 'descr2', 2.4, 'producer2', 1);
INSERT INTO `product` (`id`, `name`, `description`, `price`, `producer`, `product_group`) VALUES (3, 'product3', 'descr3', 1.4, 'producer3', 2);
INSERT INTO `product` (`id`, `name`, `description`, `price`, `producer`, `product_group`) VALUES (4, 'product4', 'descr4', 5.4, 'producer4', 2);
INSERT INTO `product` (`id`, `name`, `description`, `price`, `producer`, `product_group`) VALUES (5, 'product5', 'descr5', 2.4, 'producer4', 3);
INSERT INTO `product` (`id`, `name`, `description`, `price`, `producer`, `product_group`) VALUES (6, 'product6', 'descr6', 7.4, 'producer4', 3);

INSERT INTO `warehouse` (`id`, `name`, `address`) VALUES (1, 'warehouse1', 'address1');
INSERT INTO `warehouse` (`id`, `name`, `address`) VALUES (2, 'warehouse2', 'address2');

INSERT INTO `warehouse_product` (`product_id`, `warehouse_id`, `quantity`) VALUES (1, 1, 10);
INSERT INTO `warehouse_product` (`product_id`, `warehouse_id`, `quantity`) VALUES (2, 1, 3);
INSERT INTO `warehouse_product` (`product_id`, `warehouse_id`, `quantity`) VALUES (3, 2, 4);
INSERT INTO `warehouse_product` (`product_id`, `warehouse_id`, `quantity`) VALUES (4, 2, 6);

INSERT INTO `order` (`id`, `client`, `status`) VALUES (1, 'client1', 0);
INSERT INTO `order` (`id`, `client`, `status`) VALUES (2, 'client2', 1);
INSERT INTO `order` (`id`, `client`, `status`) VALUES (3, 'client3', 2);

INSERT INTO `order_to_warehouse_to_product` (`product_id`, `warehouse_id`, `order_id`, `amount`) VALUES (1, 1, 1, 2);
INSERT INTO `order_to_warehouse_to_product` (`product_id`, `warehouse_id`, `order_id`, `amount`) VALUES (3, 2, 2, 1);
INSERT INTO `order_to_warehouse_to_product` (`product_id`, `warehouse_id`, `order_id`, `amount`) VALUES (2, 1, 3, 3);

INSERT INTO `booking` (`id`, `booking_time`, `due_time`) VALUES (1, CURRENT_TIMESTAMP, '2022-01-25 21:06:31.000000');

INSERT INTO `booking_to_warehouse_to_product` (`product_id`, `warehouse_id`, `booking_id`, `amount`) VALUES (1, 1, 1, 2);

