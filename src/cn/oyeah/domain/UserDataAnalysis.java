package cn.oyeah.domain;

/**
 * 用户数据分析实体类
 * @author xiaochen	2011-12-16
 *
 */
public class UserDataAnalysis {
	
	private String userId;
	private int price;
	private String gameName;
	
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	

}
