package cn.oyeah.service.impl;

import java.util.List;
import java.util.Map;

import cn.oyeah.dao.RechargeDao;
import cn.oyeah.service.IRechargeGame;
import cn.oyeah.util.DaoManager;

public class RechargeGameServiceImpl implements IRechargeGame {
	

	private static RechargeDao rechargeDao;
	
	static {
		rechargeDao = (RechargeDao)DaoManager.getDao("rechargeDao");
	}

	public Map<String, Integer> getSubscribeDetail(int providerId, String productIds,String startTime,
			String endTime) {
		return rechargeDao.getSubscribeDetail(providerId, productIds,startTime, endTime);
	}

	public Map<String,String> getTableColumns(int providerId, String productIds,String startTime, String endTime) {
		return rechargeDao.getTableColumns(providerId, productIds,startTime, endTime);
	}

	public List<String> getTableRows(int providerId, String productIds,String startTime, String endTime) {
		return rechargeDao.getTableRows(providerId, productIds,startTime, endTime);
	}

	public Map<String, Integer> getAllSubscribeDetail(int providerId, String productIds,String startTime,
			String endTime) {
		return rechargeDao.getAllSubscribeDetail(providerId, productIds,startTime, endTime);
	}



}
