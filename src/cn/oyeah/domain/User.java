package cn.oyeah.domain;

import java.util.Date;

public class User {
	
	//管理员ID
	private int id;
	
	//管理员名称
	private String name;
	
	//密码
	private String passWord;
	
	//管理员角色
	private String role;
	
	//管理员权限值
	private int authority;
	
	//创建的时间
	private Date createTime;
	
	//最后登入时间
	private Date loginTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	

}
