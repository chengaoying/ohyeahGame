package cn.oyeah.domain;

/**
 * jdbc配置信息
 * @author Administrator
 *
 */
public class JdbcConfig {
	
	private String dataBaseName;

	private String driverName;
	
	private String url;
	
	private String userName;
	
	private String password;
	

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}


	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + "{driverName:" + driverName + ", url:" + url + ", userName:" + userName + "}";
	}
	
}
