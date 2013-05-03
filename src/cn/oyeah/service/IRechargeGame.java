package cn.oyeah.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface IRechargeGame {
	
	/**
	 * 获取该时间段内所有游戏的充值总额
	 * @param startTime
	 * @param endTime
	 * @return Map
	 */
	public Map<String,Integer> getSubscribeDetail(int providerId, String productIds,String startTime, String endTime);
	/**
	 * 获得该时间段内的行数
	 * @param startTime
	 * @param endTime
	 * @throws ParseException 
	 */
	public List<String> getTableRows(int providerId, String productIds,String startTime, String endTime);
	/**
	 * 获取已经充值的游戏
	 * @param startTime
	 * @param endTime
	 * @return 
	 */
	public Map<String,String> getTableColumns(int providerId, String productIds,String startTime, String endTime);
	/**
	 * 获取该时间段内单款游戏充值总额
	 * @param startTime
	 * @param endTime
	 * @return Map
	 */
	public Map<String,Integer> getAllSubscribeDetail(int providerId, String productIds,String startTime, String endTime);

}
