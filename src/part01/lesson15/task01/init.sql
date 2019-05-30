create table user
(
	id serial not null
		constraint user_pk
			primary key,
	name varchar(100),
	birthday timestamp,
	login_id int,
	city varchar(50),
	email varchar(100),
	description varchar(255)
);

comment on table user is 'Пользователи';

create table role
(
	id serial not null
		constraint role_pk
			primary key,
	name varchar(100)
);

comment on table role is 'Роли пользователя';

create table user_role
(
	id serial not null
		constraint user_role_pk
			primary key,
	user_id int
		constraint user_role_user_id_fk
			references user,
	role_id int
		constraint user_role_role_id_fk
			references role
);

comment on table user_role is 'Связи пользователей и наименования их ролей';

INSERT INTO "public"."role" ("id", "name") VALUES (1, 'Administration');
INSERT INTO "public"."role" ("id", "name") VALUES (2, 'Clients');
INSERT INTO "public"."role" ("id", "name") VALUES (3, 'Billing');



