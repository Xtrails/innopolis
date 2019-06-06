create table user_
(
	id serial not null
		constraint user__pk
			primary key,
	name varchar(100),
	birthday timestamp,
	login_id int,
	city varchar(50),
	email varchar(100),
	description varchar(255)
);

comment on table user_ is 'Пользователи';

create table role_
(
	id serial not null
		constraint role__pk
			primary key,
	name varchar(100)
);

comment on table role_ is 'Роли пользователя';

create table user_role
(
	id serial not null
		constraint user_role_pk
			primary key,
	user_id int
		constraint user_role_user_id_fk
			references user_,
	role_id int
		constraint user_role_role_id_fk
			references role_
);

comment on table user_role is 'Связи пользователей и наименования их ролей';

INSERT INTO "public"."role_" ("id", "name") VALUES (1, 'Administration');
INSERT INTO "public"."role_" ("id", "name") VALUES (2, 'Clients');
INSERT INTO "public"."role_" ("id", "name") VALUES (3, 'Billing');



