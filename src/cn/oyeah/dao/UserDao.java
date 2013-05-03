package cn.oyeah.dao;

import java.util.List;
import java.util.Map;

import cn.oyeah.domain.User;
import cn.oyeah.util.PageModel;

/**
 * 
 * @author XIAOCHEN 2011-11-23
 *
 */
public interface UserDao {
	
	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	public User loadUserByName(String name);
	/**
	 * 添加用户
	 * @param u
	 */
	public void addUser(User u);
	/**
	 * 根据ID删除该用户
	 * @param id
	 */
	public boolean delUserById(int id);
	/**
	 * 查询用户列表
	 * @return
	 */
	public PageModel<User> queryUserList(int pageNo, int pageSize);
	/**
	 * 根据用户ID查询该用户
	 * @param name
	 * @return
	 */
	public void updateUserLoginTime(String name);
	/**
	 * 更改用户信息
	 * @param u
	 */
	public void updateUser(User u);
	
	/**
	 * 查寻用户余额
	 * @return
	 */
	public Map<String, Integer> queryCoin();
	
	public List<Integer> getTableRows();
	
	public List<String> getTableColumns();
	
	public int queryAllAmount();
	
	public String getUserIdByAccountId(int accountId);
	
	public String getGameNameByProductId(int productId);
}
