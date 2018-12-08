drop table if exists user_test;
create table user_test (
  id varchar(32) primary key,
  username varchar(32) unique not null,
  password varchar(32) not null,
  role_id varchar(32) not null
);
comment on table user_test is '用户表';
comment on column user_test.id is 'ID';
comment on column user_test.username is '用户名';
comment on column user_test.password is '密码';
comment on column user_test.role_id is '角色ID';

drop table if exists role_test;
create table role_test (
  id varchar(32) primary key,
  code varchar(32) unique not null,
  name varchar(32) unique not null
);
comment on table role_test is '角色表';
comment on column role_test.id is 'ID';
comment on column role_test.code is '角色代码';
comment on column role_test.name is '角色名';

drop table if exists permission_test;
create table permission_test (
  id varchar(32) primary key,
  code varchar(32) unique not null,
  name varchar(32) unique not null,
  parent_id varchar(32)
);
comment on table permission_test is '资源表';
comment on column permission_test.id is 'ID';
comment on column permission_test.code is '代码';
comment on column permission_test.name is '名称';
comment on column permission_test.parent_id is '父ID';

drop table if exists role_permission_test;
create table role_permission_test (
  role_id varchar(32) not null,
  permission_id varchar(32) not null,
  primary key(role_id,permission_id)
);
comment on table role_permission_test is '角色资源关系表';
comment on column role_permission_test.role_id is '角色ID';
comment on column role_permission_test.permission_id is '资源ID';