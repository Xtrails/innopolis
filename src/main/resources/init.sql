CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table user_
(
	id UUID not null
		DEFAULT uuid_generate_v1()
		constraint user__pk
			primary key,
	name varchar(100),
	birthday timestamp,
	login_id UUID,
	city varchar(50),
	email varchar(100),
	description varchar(255)
);

comment on table user_ is 'Пользователи';

create table role_
(
	id UUID not null
		DEFAULT uuid_generate_v1()
		constraint role__pk
			primary key,
	name varchar(100)
);

comment on table role_ is 'Роли пользователя';

create table user_role
(
	id UUID not null
		DEFAULT uuid_generate_v1()
		constraint user_role_pk
			primary key,
	user_id UUID
		constraint user_role_user_id_fk
			references user_,
	role_id UUID
		constraint user_role_role_id_fk
			references role_
);

comment on table user_role is 'Связи пользователей и наименования их ролей';

INSERT INTO "public"."role_" ("id", "name") VALUES (uuid_generate_v1(), 'Administration');
INSERT INTO "public"."role_" ("id", "name") VALUES (uuid_generate_v1(), 'Clients');
INSERT INTO "public"."role_" ("id", "name") VALUES (uuid_generate_v1(), 'Billing');



