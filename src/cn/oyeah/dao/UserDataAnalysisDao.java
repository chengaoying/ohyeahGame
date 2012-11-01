package cn.oyeah.dao;

import java.util.List;

import cn.oyeah.domain.Product;
import cn.oyeah.domain.UserDataAnalysis;
import cn.oyeah.util.PageModel;

/**
 * 用户数据分析接口
 * @author xiaochen 2011-12-16
 *
 */
public interface UserDataAnalysisDao {
	
	/**
	 * 查询用户充值所有游戏
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserDataAnalysis> queryAllUserData(String startTime, String endTime, int pageNo, int pageSize);
	
	/**
	 * 查询用户充值单款游戏
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserDataAnalysis> querySingleUserData(String startTime, String endTime, int pageNo, int pageSize, int productId);

	/**
	 * 查询所有游戏
	 */
	public List<Product> queryAllProduct(); 
	

}
