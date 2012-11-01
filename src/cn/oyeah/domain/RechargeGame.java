package cn.oyeah.domain;

public class RechargeGame {
	
	public static final byte SUBSCRIBE_COMMAND_NEWSUB = 1;		/*新用户订购*/
	public static final byte SUBSCRIBE_COMMAND_SUB = 2;			/*用户订购*/
	public static final byte SUBSCRIBE_COMMAND_UNSUB = 3;		/*用户退订*/
	public static final byte SUBSCRIBE_COMMAND_TELUNSUB = 4;	/*客服退订*/
	
	private long id;
	private int accountId;
	private String userId;
	private int amount;
	private String subscribeImplementor;
	private String subscribeType;
	private int accountSubscribeCommand;
	private int productSubscribeCommand;
	private String subscribeId;
	private String remark;
	private int productId;
	private java.util.Date time;
	private String ip;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getSubscribeImplementor() {
		return subscribeImplementor;
	}
	public void setSubscribeImplementor(String subscribeImplementor) {
		this.subscribeImplementor = subscribeImplementor;
	}
	public String getSubscribeType() {
		return subscribeType;
	}
	public void setSubscribeType(String subscribeType) {
		this.subscribeType = subscribeType;
	}
	public int getAccountSubscribeCommand() {
		return accountSubscribeCommand;
	}
	public void setAccountSubscribeCommand(int accountSubscribeCommand) {
		this.accountSubscribeCommand = accountSubscribeCommand;
	}
	public int getProductSubscribeCommand() {
		return productSubscribeCommand;
	}
	public void setProductSubscribeCommand(int productSubscribeCommand) {
		this.productSubscribeCommand = productSubscribeCommand;
	}
	public String getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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

}
