package cn.oyeah.dao;

import cn.oyeah.domain.UserSubscribeRecord;
import cn.oyeah.util.PageModel;

public interface UserRecordQueryDao {
	
	/**
	 * 通过userId查询用户充值记录
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserSubscribeRecord> queryUserSubscribeByUserId(String userId, int pageNo, int pageSize);
	/**
	 * 通过userId查询用户道具购买记录
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserSubscribeRecord> queryUserPurchaseByUserId(String userId, int pageNo, int pageSize);
	

}
