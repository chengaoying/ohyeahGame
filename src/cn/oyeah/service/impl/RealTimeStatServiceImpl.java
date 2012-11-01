package cn.oyeah.service.impl;

import java.util.Map;

import cn.oyeah.dao.RealStatisticDao;
import cn.oyeah.service.IRealTimeStatService;
import cn.oyeah.util.DaoManager;

public class RealTimeStatServiceImpl implements IRealTimeStatService {

	private static RealStatisticDao realStatistisDao;
	static {
		realStatistisDao = (RealStatisticDao)DaoManager.getDao("realStatisticDao");
	}
	
	public Map<String, String> getPurchaseGameName(String time) {
		return realStatistisDao.getPurchaseGameName(time);
	}

	public Map<String, Integer> queryRealSubscribe(String time) {
		return realStatistisDao.queryRealSubscribe(time);
	}

	public Map<String, Integer> getAllSubscribeDetail(String time) {
		return realStatistisDao.getAllSubscribeDetail(time);
	}

}
