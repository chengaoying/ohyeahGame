use test;

select top 9 *
from (
  select row_number()over(order by tb.t desc,tb.productId asc,tb.pCount desc) as rowNumber ,*
  from (
		select CONVERT(varchar(100), time, 23) as t,productId,propId,sum(propCount) as pCount ,amount
		from [PurchaseRecord]
		where time >= '2011-11-30' and time <= '2011-12-07'
			group by CONVERT(varchar(100), time, 23),productId,propId,amount
		) tb
  ) tb2
where  rowNumber > 9 * (2-1)


#userStatistic query
use test

select top 3 *
from(
	select row_number()over(order by tb.t desc) as rowNumber ,*
	from(
		select count(userId) as users, CONVERT(varchar(100), time, 23) as t,sum(amount) as a 
		from [SubscribeRecord] 
		where CONVERT(varchar(100), time, 23) >= '2011-12-01' and CONVERT(varchar(100), time, 23) <= '2011-12-13' 
		group by CONVERT(varchar(100), time, 23)
	)tb
)tb2
where rowNumber > 3 * (1-1)