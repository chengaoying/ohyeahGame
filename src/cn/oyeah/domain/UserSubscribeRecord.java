package cn.oyeah.domain;

import java.util.Date;

/**
 * 用户充值和购买道具记录实体类
 * @author xiaochen 2011-12-14
 *
 */
public class UserSubscribeRecord {
	
	private int subscribeAmount;
	private int purchaseAmount;
	private String name;
	private Date time;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSubscribeAmount() {
		return subscribeAmount;
	}
	public void setSubscribeAmount(int subscribeAmount) {
		this.subscribeAmount = subscribeAmount;
	}
	public int getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	

}
