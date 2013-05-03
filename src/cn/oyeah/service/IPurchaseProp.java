package cn.oyeah.service;

import java.util.List;

import cn.oyeah.domain.Product;
import cn.oyeah.domain.PurchaseProp;
import cn.oyeah.util.PageModel;

/**
 * 道具购买service
 * @author xiaochen  2011-12-1
 *
 */
public interface IPurchaseProp {
	/**
	 * 根据查询所有已购买的道具按时间排序
	 * @param 
	 */
	public PageModel<PurchaseProp> queryAllPurchaseProp(int providerId, String productIds, String startTime, String endTime, int pageNo, int pageSize);
	/**
	 * 根据游戏名查询已购买的道具
	 * @param gameName
	 */
	public PageModel<PurchaseProp> queryPurchaseProp(String startTime, String endTime, int id, int pageNo, int pageSize);
	/**
	 * 查询所有游戏产品
	 * @return
	 */
	public List<Product> queryAllProduct(int provideId);

}
