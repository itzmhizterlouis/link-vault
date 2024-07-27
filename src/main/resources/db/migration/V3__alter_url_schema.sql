alter table urls
    alter column folder_id drop not null,
    alter column folder_id set default null,
    drop constraint urls_url_name_key,
    add constraint urls_unique_url_in_folder unique (folder_id, name)
