package cn.oyeah.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oyeah.domain.Product;
import cn.oyeah.domain.PurchaseProp;
import cn.oyeah.service.IPurchaseProp;
import cn.oyeah.service.impl.PurchasePropServiceImpl;
import cn.oyeah.util.DateTimeUtils;
import cn.oyeah.util.PageModel;
/**
 * 道具购买
 * @author xiaochen 2011-12-1
 *
 */
public class PropPurchaseServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7749293377644346214L;
	private String command = "";
	private String startTime = "";
    private String endTime = "";
    private String sTime = "";
    private String eTime = "";
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Connection conn = (Connection)request.getAttribute("conn");
		sTime = request.getParameter("startTime");
		eTime = request.getParameter("endTime");
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
		
		command = request.getParameter("command");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = 30;
		if ("all".equals(command)) { //所有游戏购买的道具		
			queryAllProp(request, response ,pageNo, pageSize);
			
		} else if("single".equals(command)) {  //单款游戏购买的道具	
			int productId = 0;
			if (request.getParameter("productId") != null) {
				productId = Integer.parseInt(request.getParameter("productId"));
			} 
			querySingleProp(request, response ,pageNo, pageSize, productId);
		}
	}
	
	/**
	 * 查询所有游戏已经购买的道具
	 * @param request
	 * @param response
	 */
	private void queryAllProp(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize)
	throws ServletException, IOException {
		
		IPurchaseProp purchasePropSer = new PurchasePropServiceImpl();
		PageModel<PurchaseProp> pageModel = purchasePropSer.queryAllPurchaseProp(startTime, endTime, pageNo, pageSize);
		List<Product>  productList = purchasePropSer.queryAllProduct();
		request.setAttribute("productList", productList);
		request.setAttribute("pageModel", pageModel);
		//System.out.println(pageModel.getList().size());
		request.setAttribute("command", command);
		request.setAttribute("sTime", sTime);
		request.setAttribute("eTime", eTime);
		request.getRequestDispatcher("/web/propRecharge/propRecharge_list_all.jsp").forward(request, response);
	}
	/**
	 * 查询单款游戏已经购买的道具
	 * @param request
	 * @param response
	 */
	private void querySingleProp(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, int productId)
	throws ServletException, IOException {
		
		IPurchaseProp purchasePropSer = new PurchasePropServiceImpl();
		PageModel<PurchaseProp> pageModel2 = purchasePropSer.queryPurchaseProp(startTime, endTime, productId, pageNo, pageSize);
		List<Product>  productList2 = purchasePropSer.queryAllProduct();
		request.setAttribute("productList2", productList2);
		request.setAttribute("pageModel2", pageModel2); 
		//System.out.println("productList2>>"+productList2);
		//System.out.println("pageModel:::" + pageModel2.getTotalRecords());
		request.setAttribute("command", command);
		request.setAttribute("sTime", sTime);
		request.setAttribute("eTime", eTime);
		request.setAttribute("productId", productId);
		request.getRequestDispatcher("/web/propRecharge/propRecharge_list_single.jsp").forward(request, response);
	}

}
