create schema if not exists "link_vault";

create table if not exists users (
    user_id serial primary key,
    email varchar(255) not null unique,
    name varchar(1000) not null
);
