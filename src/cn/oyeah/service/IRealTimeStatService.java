package cn.oyeah.service;

import java.util.Map;

/**
 * 实时统计
 * @author xiaochen
 *
 */
public interface IRealTimeStatService {
	
	/**
	 * 查询当天充值金额
	 * @return
	 */
	public Map<String,Integer> queryRealSubscribe(String time);
	/**
	 * 获取已经充值的游戏
	 * @param startTime
	 * @param endTime
	 * @return 
	 */
	public Map<String,String> getPurchaseGameName(String time);
	/**
	 * 获取该时间段内单款游戏充值总额
	 * @param startTime
	 * @param endTime
	 * @return Map
	 */
	public Map<String,Integer> getAllSubscribeDetail(String time);

}
