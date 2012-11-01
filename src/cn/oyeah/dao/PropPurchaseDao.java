package cn.oyeah.dao;

import java.util.List;

import cn.oyeah.domain.Product;
import cn.oyeah.domain.PurchaseProp;
import cn.oyeah.util.PageModel;

/**
 * 道具购买Dao
 * @author xiaochen  2011-12-1
 *
 */
public interface PropPurchaseDao {
	/**
	 * 根据查询所有已购买的道具按时间排序
	 * @param 
	 */
	public PageModel<PurchaseProp> queryAllPurchaseProp(String startTime, String endTime, int pageNo, int pageSize);
	/**
	 * 根据游戏ID查询已购买的道具
	 * @param gameName
	 */
	public PageModel<PurchaseProp> queryPurchasePropById(String startTime, String endTime, int productId, int pageNo, int pageSize);
	
	/**
	 * 查询所有游戏
	 */
	public List<Product> queryAllProduct();

}
