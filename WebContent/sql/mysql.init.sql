drop table if exists Administrator;
create table if not exists Administrator (
	id int not null primary key auto_increment,
	name varchar(30) not null,
	password varchar(30) not null,
	role varchar(20) not null,
	authority varchar(20) not null,
	createtime datetime ,
	logintime datetime 
);

drop table if exists DataDictionary;
create table if not exists DataDictionary (
	id int not null primary key auto_increment,
	name varchar(30) not null,
	value varchar(30) not null
);


insert into Administrator(name,password,role,authority,createtime,logintime) values('ohyeah','ohyeah','admin','2','2011-12-02','2011-12-02');
insert into DataDictionary(name,value) values('authority', '1');
insert into DataDictionary(name,value) values('authority', '2');
insert into DataDictionary(name,value) values('authority', '3');
insert into DataDictionary(name,value) values('role', 'admin');
insert into DataDictionary(name,value) values('role', 'worker');
insert into DataDictionary(name,value) values('role', 'partner');

