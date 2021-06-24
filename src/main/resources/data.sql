drop table if exists user;

create table user (
    id varchar(60) PRIMARY KEY,
    first_name varchar(100) NOT NULL,
    middle_name varchar(100) NULL,
    last_name varchar(100) NOT NULL
);

insert into user (id, first_name, middle_name, last_name) values
 ('097e1c2e-8ba3-463e-96d2-7a08f32944a6','Justin','Marquis','Wheeler'),
 ('b1f6c877-c4a2-4e00-a986-2cbed56e257f','James',null,'Wagon'),
 ('d7d77a1b-d0e4-49bb-804d-895db58027cb','John','Smith','Doe'),
 ('5f99aae9-7426-4e5b-932a-4f7066785e63','Jack',null,'Pool'),
 ('d54cbbb5-96f7-496e-8142-ce4966061940','Joan','Marie','River');
