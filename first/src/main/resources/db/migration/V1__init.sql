create table product_group
(
    id              int primary key auto_increment,
    name            varchar(30) not null,
    description     varchar (255),
    unique unique_name (name)
);

create table product
(
    id              int primary key auto_increment,
    name            varchar(30) not null,
    description     varchar (255),
    price           double not null,
    producer        varchar (30) not null,
    product_group   int not null,
    unique unique_name (name),
    constraint fk_product_to_group foreign key (id) references product_group(id) on delete cascade
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
    id              int primary key auto_increment,
    product_id      int not null,
    warehouse_id    int not null,
    quantity        int not null,
    constraint fk_wp_to_product foreign key (product_id) references product(id),
    constraint fk_wp_to_warehouse foreign key (warehouse_id) references warehouse(id)
);

create table `order`
(
    id              int primary key auto_increment,
    client          varchar(30) not null,
    status          bit not null
);

create table order_to_warehouse_product
(
    id                      int primary key auto_increment,
    warehouse_product_id    int not null,
    order_id                int not null,
    amount                  int not null,
    constraint fk_owp_to_wp foreign key (warehouse_product_id) references warehouse(id),
    constraint fk_owp_to_order foreign key (order_id) references `order`(id)
);

create table booking
(
    id                      int primary key auto_increment,
    booking_time            datetime not null default CURRENT_TIMESTAMP,
    due_time                datetime not null
);

create table booking_to_warehouse_product
(
    id                      int primary key auto_increment,
    warehouse_product_id    int not null,
    booking_id              int not null,
    amount                  int not null,
    constraint fk_bwp_to_wp foreign key (warehouse_product_id) references warehouse(id),
    constraint fk_bwp_to_booking foreign key (booking_id) references booking(id)
);