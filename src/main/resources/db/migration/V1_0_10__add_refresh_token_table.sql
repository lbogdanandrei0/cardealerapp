create table refresh_token(
    id int primary key generated by default as identity,
    token varchar(100) not null unique,
    created_at timestamp default current_timestamp
);