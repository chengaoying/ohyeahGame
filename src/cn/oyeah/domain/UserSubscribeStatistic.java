package cn.oyeah.domain;

import java.util.Date;

/**
 * 用户充值统计实体类
 * @author xiaochen 2011-12-12
 *
 */
public class UserSubscribeStatistic {
	
	private int dayCount;
	private int newCount;
	private int totalCount;
	private int price;
	private Date time;
	
	public int getDayCount() {
		return dayCount;
	}
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}
	public int getNewCount() {
		return newCount;
	}
	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
