package cn.oyeah.domain;

/**
 * 道具购买实体类
 * @author xiaochen 2011-12-2
 *
 */
public class PurchaseProp implements java.io.Serializable  {

	private static final long serialVersionUID = 6393756701907331139L;
	private long id;
	private int accountId;
	private String userId;
	private int productId;
	private int propId;
	private int propCount;
	private int amount;
	private String remark;
	private java.util.Date time;
	private String ip;
	
	private String gameName;
	private String propName;
	
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setPropId(int propId) {
		this.propId = propId;
	}
	public int getPropId() {
		return propId;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public void setPropCount(int propCount) {
		this.propCount = propCount;
	}
	public int getPropCount() {
		return propCount;
	}

}
