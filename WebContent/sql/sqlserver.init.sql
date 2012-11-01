if object_id('[Account]') is null create table [Account](
	accountId int not null identity(10000, 1),
	nickName varchar(16),
	pwdMd5 varchar(32),
	privilege int not null default 0,
	userId varchar(16) not null unique,
	scores int not null default 0,
	goldCoin int not null default 0,
	gameCoin int not null default 0,
	createTime datetime not null,
	updateTime datetime not null,
	state int not null default 0,
	primary key(accountId)
);
	
if object_id('[AccountPermission]') is null create table [AccountPermission](
	accountId int not null,
	daySubscribeLimit int not null default 0,
	monthSubscribeLimit int not null default 0,
	lastSubscribeTime datetime,
	daySubscribeAmount int not null default 0,
	monthSubscribeAmount int not null default 0,
	totalSubscribeAmount int not null default 0,
	accessPermission int not null default 0,
	subscribePermission int not null default 0,
	primary key(accountId)
);

