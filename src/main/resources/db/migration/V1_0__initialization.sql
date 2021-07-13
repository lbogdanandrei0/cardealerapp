create type car_brand as enum('Tesla', 'Audi', 'Volkswagen', 'BMW', 'Honda', 'Mercedes');
create type car_type as enum('Sedan', 'Coupe', 'SUV', 'Hatchback', 'Sport');

create table dealer(
    id bigint primary key,
    name varchar(50) not null,
    address varchar(100) not null,
    created_at timestamp default current_timestamp
);

create table car(
    id bigint primary key,
    location int not null,
    brand car_brand not null,
    type car_type,
    is_new boolean default false,
    had_accident boolean default false,
    engine_capacity smallint,
    engine_power smallint,
    date_of_fabrication date,
    price float,
    description text
--    constraint dealer_id
--        foreign key(dealer)
--            references dealer(id)
);