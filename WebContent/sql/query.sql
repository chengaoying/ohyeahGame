#query all 

select count(*) as s, time, userId, productId,productSubscribeCommand,sum(amount) as a  
from SubscribeRecord 
where date(time) ='2011-12-09'
group by userId;

#query new

select count(*) as s,time, userId, productId,productSubscribeCommand,sum(amount) as a  
from SubscribeRecord 
where date(time) ='2011-12-09' and productSubscribeCommand = 1
group by userId;

#query current 

select count(*) as s,time, userId, productId,productSubscribeCommand,sum(amount) as a  
from SubscribeRecord 
where date(time) ='2011-12-09' and  productSubscribeCommand = 2
group by userId; 

# query default userSubscribe

select count(userId) as users, date(time) as t,sum(amount) as a
from SubscribeRecord
where date(time) >= '2011-12-01' and date(time) <= '2011-12-09'
group by date(time) order by t desc;

# query usersubscribeRecord

select date(time) as t, sum(amount) as a 
from SubscribeRecord
where userId=113001
group by date(time)

# query userPurchaseRecord

select date(time) as t, sum(amount) as a
from PurchaseRecord
where userId=113001
group by date(time);