package cn.oyeah.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oyeah.domain.Product;
import cn.oyeah.domain.User;
import cn.oyeah.service.IPurchaseProp;
import cn.oyeah.service.IRechargeGame;
import cn.oyeah.service.impl.PurchasePropServiceImpl;
import cn.oyeah.service.impl.RechargeGameServiceImpl;
import cn.oyeah.util.DateTimeUtils;

/**
 * 查询游戏订购记录
 * @author xiaochen 2011-12-3
 *
 */
public class RechargeGameServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500042671045825972L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String startTime = "", sTime = request.getParameter("startTime");
		String endTime = "", eTime = request.getParameter("endTime");
		if (sTime == null){
			startTime = DateTimeUtils.getStartTime() + " 00:00:00"; //查询需用的时间
			sTime = DateTimeUtils.getStartTime();  //页面显示需用的时间
		} else {
			startTime = request.getParameter("startTime") + " 00:00:00";
		}
		if (eTime == null){
			endTime = DateTimeUtils.getEndTime() + " 23:59:59";
			eTime = DateTimeUtils.getEndTime();
		} else {
			endTime = request.getParameter("endTime") + " 23:59:59"; 
		}
		
		User loginUser = (User)request.getSession().getAttribute("user");
		IPurchaseProp purchasePropSer = new PurchasePropServiceImpl();
		List<Product>  products = purchasePropSer.queryAllProduct(loginUser.getProviderID());
		String productIds = "";
		if(loginUser.getProviderID()!=1){
			for(Product p:products){
				productIds += p.getProductId()+",";
			}
			productIds = productIds.substring(0, productIds.length()-1);
		}
		
		IRechargeGame rechargeSer = new RechargeGameServiceImpl();
		Map<String,Integer> subscribeMap = rechargeSer.getSubscribeDetail(loginUser.getProviderID(),productIds,startTime, endTime);
		Map<String,Integer> allSubscribeMap = rechargeSer.getAllSubscribeDetail(loginUser.getProviderID(),productIds,startTime, endTime);
		List<String> timeList = rechargeSer.getTableRows(loginUser.getProviderID(),productIds,startTime, endTime);
		Map<String,String> productList = rechargeSer.getTableColumns(loginUser.getProviderID(),productIds,startTime, endTime);
		request.setAttribute("subscribeMap", subscribeMap);
		request.setAttribute("allSubscribeMap",allSubscribeMap);
		request.setAttribute("timeList", timeList);//页面的行
		request.setAttribute("productList", productList);//页面的列
		request.setAttribute("sTime", sTime);
		request.setAttribute("eTime", eTime);
		request.getRequestDispatcher("/web/recharge/recharge_list.jsp").forward(request, response);
	}

}
