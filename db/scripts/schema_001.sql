CREATE TABLE IF NOT EXISTS engine(
    id SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE IF NOT EXISTS driver(
    id SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE IF NOT EXISTS car(
    id SERIAL PRIMARY KEY,
    name varchar(255),
    engine_id int references engine(id)
);

create table if not exists history_owner(
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);