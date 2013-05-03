package cn.oyeah.service.impl;

import java.util.List;

import cn.oyeah.dao.UserDataAnalysisDao;
import cn.oyeah.domain.Product;
import cn.oyeah.domain.UserDataAnalysis;
import cn.oyeah.service.IUserDataService;
import cn.oyeah.util.DaoManager;
import cn.oyeah.util.PageModel;

public class UserDataServiceImpl implements IUserDataService {
	
	private static UserDataAnalysisDao userDataAnalysisDao;
	static {
		userDataAnalysisDao = (UserDataAnalysisDao)DaoManager.getDao("userDataAnalysisDao");
	}

	public PageModel<UserDataAnalysis> queryAllUserData(int providerId,String productIds,String startTime,
			String endTime, int pageNo, int pageSize) {
		return userDataAnalysisDao.queryAllUserData(providerId,productIds,startTime, endTime, pageNo, pageSize);
	}

	public PageModel<UserDataAnalysis> querySingleUserData(int providerId,String productIds,String startTime,
			String endTime, int pageNo, int pageSize, int productId) {
		return userDataAnalysisDao.querySingleUserData(providerId,productIds,startTime, endTime, pageNo, pageSize, productId);
	}

	public List<Product> queryAllProduct(int providerId) {
		return userDataAnalysisDao.queryAllProduct(providerId);
	}

}
