package cn.oyeah.service;

import java.util.List;

import cn.oyeah.domain.Product;
import cn.oyeah.domain.UserDataAnalysis;
import cn.oyeah.util.PageModel;

/**
 * 用户数据分析
 * @author Administrator
 *
 */
public interface IUserDataService {
	
	/**
	 * 查询用户充值所有游戏
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserDataAnalysis> queryAllUserData(int providerId,String productIds,String startTime, String endTime, int pageNo, int pageSize);
	
	/**
	 * 查询用户充值单款游戏
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserDataAnalysis> querySingleUserData(int providerId,String productIds,String startTime, String endTime, int pageNo, int pageSize, int productId);
	/**
	 * 查询所有游戏
	 */
	public List<Product> queryAllProduct(int providerId); 
	


}
