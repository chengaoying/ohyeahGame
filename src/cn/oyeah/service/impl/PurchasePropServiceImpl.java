package cn.oyeah.service.impl;

import java.util.List;

import cn.oyeah.dao.PropPurchaseDao;
import cn.oyeah.domain.Product;
import cn.oyeah.domain.PurchaseProp;
import cn.oyeah.service.IPurchaseProp;
import cn.oyeah.util.DaoManager;
import cn.oyeah.util.PageModel;

public class PurchasePropServiceImpl implements IPurchaseProp {
	
	private static PropPurchaseDao propPurchaseDao;
	static {
		propPurchaseDao = (PropPurchaseDao)DaoManager.getDao("propPurchaseDao");
	}

	/**
	 * 查询所有游戏已经购买的道具
	 * @param pageNo, pageSize
	 */
	public PageModel<PurchaseProp> queryAllPurchaseProp(String startTime, String endTime, int pageNo, int pageSize) {
		return propPurchaseDao.queryAllPurchaseProp(startTime, endTime, pageNo, pageSize);
	}

	/**
	 * 查询单款已经购买的道具
	 * @param id, pageNo, pageSize
	 */
	public PageModel<PurchaseProp> queryPurchaseProp(String startTime, String endTime, int id,
			int pageNo, int pageSize) {
		return propPurchaseDao.queryPurchasePropById(startTime, endTime, id, pageNo, pageSize);
	}
	
	/**
	 * 查询所有游戏产品
	 */
	public List<Product> queryAllProduct() {
		return propPurchaseDao.queryAllProduct();
	}
}
