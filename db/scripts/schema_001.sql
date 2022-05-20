create table if not exists engines(
    id serial primary key,
    name varchar(255)
);

create table if not exists drivers(
    id serial primary key,
    name varchar(255)
);

create table if not exists users(
    id serial primary key,
    name varchar(100) not null,
    email varchar(100) unique not null,
    password varchar(100) not null
);

create table if not exists models(
    id serial primary key,
    name varchar (150) not null
);

create table if not exists brands(
    id serial primary key,
    name varchar(150) unique not null
);

create table if not exists bodies(
    id serial primary key,
    name varchar(150) unique not null
);

create table if not exists cars(
    id serial primary key,
    name varchar(255),
    engine_id int references engines(id),
    brand_id int not null references brands(id),
    body_id int not null references bodies(id)
);

create table if not exists history_owner(
    id serial primary key,
    driver_id int not null references drivers(id),
    car_id int not null references cars(id)
);

create table if not exists adverts(
    id serial primary key,
    created timestamp,
    description text,
    car_id int not null references cars(id),
    photo bytea,
    sold boolean default false,
    user_id int not null references users(id)
);