package cn.oyeah.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.oyeah.dao.UserDao;
import cn.oyeah.domain.User;
import cn.oyeah.service.IUserService;
import cn.oyeah.util.DaoManager;
import cn.oyeah.util.PageModel;

public class UserServiceImpl implements IUserService {
	
	private static UserDao userDao;
	
	static {
		userDao = (UserDao)DaoManager.getDao("userDao");
	}
	
	private static  UserServiceImpl userServiceImpl = new UserServiceImpl();
	
	private UserServiceImpl(){}
	
	public static UserServiceImpl getInstance(){
		return userServiceImpl;
	}
    
	public void  addUser(User u) {
		userDao.addUser(u);
	}
	
	public boolean delUserById(int id) {
		return userDao.delUserById(id);
	}

	public User loginValidate(String name,String passWord) {
		User user = userDao.loadUserByName(name);
		if (user!=null && StringUtils.equals(user.getPassWord(), passWord)){
			return user;
		}
		return null;
	}

	public PageModel<User> queryUserList(int pageNo, int pageSize) {
		PageModel<User> pageModel = userDao.queryUserList(pageNo, pageSize);
		return pageModel;
	}

	public void updateLoginTime(String name) {
		userDao.updateUserLoginTime(name);
	}

	public void updateUser(User u) {
		userDao.updateUser(u);
		
	}

	public User loginValidate(String userName) {
		User user = userDao.loadUserByName(userName);	
		return user;
	}

	@Override
	public Map<String, Integer> queryCoin() {
		return userDao.queryCoin();
	}

	@Override
	public List<Integer> getTableRows() {
		return userDao.getTableRows();
	}

	@Override
	public List<String> getTableColumns() {
		return userDao.getTableColumns();
	}

	@Override
	public int queryAllAmount() {
		return userDao.queryAllAmount();
	}

	@Override
	public String getUserIdByAccountId(int accountId) {
		return userDao.getUserIdByAccountId(accountId);
	}

	@Override
	public String getGameNameByProductId(int productId) {
		return userDao.getGameNameByProductId(productId);
	}

}
