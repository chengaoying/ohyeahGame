package cn.oyeah.service.impl;

import java.util.List;

import cn.oyeah.dao.UserSubscribeRecordDao;
import cn.oyeah.domain.Product;
import cn.oyeah.domain.UserSubscribeStatistic;
import cn.oyeah.service.IUserSubscribe;
import cn.oyeah.util.DaoManager;
import cn.oyeah.util.PageModel;

public class UserSubscribeServiceImpl implements IUserSubscribe {

	private static UserSubscribeRecordDao userSubscribeDao;
	static {
		userSubscribeDao = (UserSubscribeRecordDao)DaoManager.getDao("userSubscribeDao");
	}
	
	/**
	 * 查询默认时间段的用户充值记录数
	 * @param pageNo
	 * @param pageSize
	 */
	public PageModel<UserSubscribeStatistic> queryAllUserSubscribeRecord(
			int pageNo, int pageSize) {
		return userSubscribeDao.queryAllUserSubscribeRecord(pageNo, pageSize);
	}

	/**
	 * 按时间条件查询用户充值记录数
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 */
	public PageModel<UserSubscribeStatistic> queryUserSubscribeRecord(
			String startTime, String endTime, int pageNo, int pageSize,int productId) {
		return userSubscribeDao.queryUserSubscribeRecord(startTime, endTime, pageNo, pageSize, productId);
	}

	public List<Product> queryAllProduct() {
		return userSubscribeDao.queryAllProduct();
	}


}
