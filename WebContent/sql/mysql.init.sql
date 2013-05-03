drop table if exists Administrator;
create table if not exists Administrator (
	id int not null primary key auto_increment,
	name varchar(30) not null,
	password varchar(30) not null,
	role varchar(20) not null,
	authority varchar(20) not null,  
	providerId int not null,
	createtime datetime ,
	logintime datetime 
);

drop table if exists DataDictionary;
create table if not exists DataDictionary (
	id int not null primary key auto_increment,
	name varchar(30) not null,
	value varchar(30) not null
);


insert into Administrator(name,password,role,authority,providerId,createtime,logintime) values('ohyeah','ohyeah','admin','3',1,'2011-12-02','2011-12-02');
insert into DataDictionary(name,value) values('authority', '3');
insert into DataDictionary(name,value) values('authority', '2');
insert into DataDictionary(name,value) values('authority', '1');
insert into DataDictionary(name,value) values('role', 'admin');
insert into DataDictionary(name,value) values('role', 'worker');
insert into DataDictionary(name,value) values('role', 'partner');

insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',67,'test',1,5,'test','2013-4-1:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',68,'test',1,5,'test','2013-4-2:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',69,'test',1,5,'test','2013-4-3:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',70,'test',1,5,'test','2013-4-4:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',71,'test',1,5,'test','2013-4-5:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',72,'test',1,5,'test','2013-4-6:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',73,'test',1,5,'test','2013-4-7:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',74,'test',1,5,'test','2013-4-8:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',75,'test',1,5,'test','2013-4-9:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',76,'test',1,5,'test','2013-4-10:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',77,'test',1,5,'test','2013-4-11:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',14,'SheepFarmSH',78,'test',1,5,'test','2013-4-12:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',15,'SheepFarmSH',79,'test',1,5,'test','2013-4-13:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',15,'SheepFarmSH',80,'test',1,5,'test','2013-4-14:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',15,'SheepFarmSH',81,'test',1,5,'test','2013-4-15:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',15,'SheepFarmSH',82,'test',1,5,'test','2013-4-16:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',15,'SheepFarmSH',83,'test',1,5,'test','2013-4-17:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',15,'SheepFarmSH',84,'test',1,5,'test','2013-4-18:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',15,'SheepFarmSH',85,'test',1,5,'test','2013-4-19:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',16,'SheepFarmSH',108,'test',1,5,'test','2013-4-20:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',16,'SheepFarmSH',109,'test',1,5,'test','2013-4-21:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',16,'SheepFarmSH',110,'test',1,5,'test','2013-4-22:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',16,'SheepFarmSH',111,'test',1,5,'test','2013-4-23:14:29','127.0.0.1');
insert into purchaserecord(accountId, userId, productId, productName, propId, propName,propCount, amount, remark, time, ip) values(10000,'igsuper00',16,'SheepFarmSH',112,'test',1,5,'test','2013-4-24:14:29','127.0.0.1');


select date(time) as t,productId, productName, propId, propName, sum(propCount) as pCount,amount from PurchaseRecord 
					 where time >= ' +startTime+' and time <= ' + endTime
					 and productId in(14,15,16)
			        ' group by  propId order by sum(propCount) desc limit 0, 10;