package cn.oyeah.service;


import cn.oyeah.domain.User;
import cn.oyeah.util.PageModel;

public interface IUserService {
	/**
	 * 登入验证
	 * @param userName
	 * @return
	 */
	public User loginValidate(String userName,String password);
	/**
	 * 查询是否重名
	 * @param userName
	 * @return
	 */
	public User loginValidate(String userName);
	/**
	 * 添加用户
	 * @param u
	 */
	public void addUser(User u);
	/**
	 * 删除单个用户
	 * @param id
	 */
	public boolean delUserById(int id);
	/**
	 * 查询用户列表
	 * @return
	 */
	public PageModel<User> queryUserList(int pageNo, int pageSize);
	/**
	 * 更改管理员最后登入时间
	 * @param id
	 */
	public void updateLoginTime(String name);
	/**
	 * 更改用户信息
	 * @param u
	 */
	public void updateUser(User u);

}
