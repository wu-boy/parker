drop table if exists user_test;
create table user_test(
  id varchar(32) primary key,
  username varchar(32) unique not null,
  password varchar(32) not null
);