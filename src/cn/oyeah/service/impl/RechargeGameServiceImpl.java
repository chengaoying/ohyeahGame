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

	public Map<String, Integer> getSubscribeDetail(String startTime,
			String endTime) {
		return rechargeDao.getSubscribeDetail(startTime, endTime);
	}

	public Map<String,String> getTableColumns(String startTime, String endTime) {
		return rechargeDao.getTableColumns(startTime, endTime);
	}

	public List<String> getTableRows(String startTime, String endTime) {
		return rechargeDao.getTableRows(startTime, endTime);
	}

	public Map<String, Integer> getAllSubscribeDetail(String startTime,
			String endTime) {
		return rechargeDao.getAllSubscribeDetail(startTime, endTime);
	}



}
