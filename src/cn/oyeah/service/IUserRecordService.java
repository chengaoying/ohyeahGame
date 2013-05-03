package cn.oyeah.service;

import cn.oyeah.domain.UserSubscribeRecord;
import cn.oyeah.util.PageModel;

public interface IUserRecordService {
	
	/**
	 * 通过userId查询用户充值记录
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserSubscribeRecord> queryUserSubscribeByUserId(int providerId, String productIds,String userId, int pageNo, int pageSize);
	/**
	 * 通过userId查询用户道具购买记录
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserSubscribeRecord> queryUserPurchaseByUserId(int providerId, String productIds,String userId, int pageNo, int pageSize);


}
