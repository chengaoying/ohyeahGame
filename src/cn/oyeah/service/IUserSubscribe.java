package cn.oyeah.service;

import java.util.List;

import cn.oyeah.domain.Product;
import cn.oyeah.domain.UserSubscribeStatistic;
import cn.oyeah.util.PageModel;

/**
 * 查询用户充值数
 * @author xiaochen 2011-12-12
 *
 */
public interface IUserSubscribe {
	
	/**
	 * 默认查询一个月的充值用户
	 * @param pageNo
	 * @param pageSize
	 */
	public PageModel<UserSubscribeStatistic> queryAllUserSubscribeRecord(int pageNo, int pageSize);
	
	/**
	 * 按时间条件查询充值用户记录
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserSubscribeStatistic> queryUserSubscribeRecord(String startTime, String endTime, int pageNo, int pageSize,int productId);
	/**
	 * 查询所有游戏
	 */
	public List<Product> queryAllProduct();
	

}
