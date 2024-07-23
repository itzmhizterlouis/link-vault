create schema if not exists "link_vault";

create table if not exists users (
    user_id serial primary key,
    email varchar(255) not null unique,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,

    locked boolean not null default false,
    enabled boolean not null default true,
    deleted boolean not null default false
);
