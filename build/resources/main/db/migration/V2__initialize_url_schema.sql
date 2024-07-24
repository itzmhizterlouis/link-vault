create table if not exists folders (

    folder_id   serial primary key,
    folder_name varchar(255) not null unique,
    user_id int not null,

    unique (folder_name, user_id),

    constraint fk_folder_user_id foreign key (user_id)
                                   references users(user_id)
);


create table if not exists urls (

    url_id serial primary key,
    url varchar(3000),
    name varchar(3000),
    user_id int not null,
    folder_id int not null,

    unique (url, name),

    constraint fk_url_user_id foreign key (user_id)
                                references users(user_id),

    constraint fk_urls_folder_id foreign key (folder_id)
        references folders(folder_id)
);

create table if not exists tags (

    url_id int not null,
    tag varchar(255) not null,

    constraint fk_tags_url_id foreign key (url_id) references urls(url_id)
);
