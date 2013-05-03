package cn.oyeah.service.impl;

import cn.oyeah.dao.UserRecordQueryDao;
import cn.oyeah.domain.UserSubscribeRecord;
import cn.oyeah.service.IUserRecordService;
import cn.oyeah.util.DaoManager;
import cn.oyeah.util.PageModel;

public class UserRecordServiceImpl implements IUserRecordService {
	
	private static UserRecordQueryDao userRecordQueryDao;
	static {
		userRecordQueryDao = (UserRecordQueryDao)DaoManager.getDao("userRecordQueryDao");
	}


	public PageModel<UserSubscribeRecord> queryUserSubscribeByUserId(
			int providerId, String productIds,String userId, int pageNo, int pageSize) {
		return userRecordQueryDao.queryUserSubscribeByUserId(providerId, productIds,userId, pageNo, pageSize);
	}


	public PageModel<UserSubscribeRecord> queryUserPurchaseByUserId(int providerId, String productIds,String userId,
			int pageNo, int pageSize) {
		return userRecordQueryDao.queryUserPurchaseByUserId(providerId, productIds,userId, pageNo, pageSize);
	}

}
