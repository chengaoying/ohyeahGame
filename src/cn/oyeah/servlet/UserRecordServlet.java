package cn.oyeah.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import cn.oyeah.domain.Product;
import cn.oyeah.domain.User;
import cn.oyeah.domain.UserSubscribeRecord;
import cn.oyeah.service.IUserDataService;
import cn.oyeah.service.IUserRecordService;
import cn.oyeah.service.impl.UserDataServiceImpl;
import cn.oyeah.service.impl.UserRecordServiceImpl;
import cn.oyeah.util.PageModel;

/**
 * 用户充值(道具购买)记录查询
 * @author xiaochen 2011-12-14 am
 *
 */
public class UserRecordServlet extends HttpServlet {


	private static final long serialVersionUID = 3214097995493959723L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = 20;
		String userId = request.getParameter("userId");
		String subscribeId = request.getParameter("subscribeId");
		if(StringUtils.equals(subscribeId, "subscribe")){
			this.querySubscribe(request, response,userId,pageNo,pageSize);
		} else if(StringUtils.equals(subscribeId, "propPurchase")){
			this.queryPurchase(request, response, userId, pageNo, pageSize);
		}
	}
	
	/**
	 * 用户游戏充值查询
	 * @param request
	 * @param response
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @throws ServletException
	 * @throws IOException
	 */
	private void querySubscribe(HttpServletRequest request, HttpServletResponse response, String userId, int pageNo, int pageSize)
		throws ServletException, IOException {
		User loginUser = (User)request.getSession().getAttribute("user");
		IUserDataService userDataService = new UserDataServiceImpl();
		List<Product>  productList = userDataService.queryAllProduct(loginUser.getProviderID());
		String productIds = "";
		if(loginUser.getProviderID()!=1){
			for(Product p:productList){
				productIds += p.getProductId()+",";
			}
			productIds = productIds.substring(0, productIds.length()-1);
		}

		IUserRecordService userRecordService = new UserRecordServiceImpl();
		PageModel<UserSubscribeRecord> userSubscribeModel = userRecordService.queryUserSubscribeByUserId(loginUser.getProviderID(),productIds,userId, pageNo, pageSize);
		request.setAttribute("userSubscribeModel", userSubscribeModel);
		request.setAttribute("userId", userId);
		request.getRequestDispatcher("/web/userQuery/userSubscribe.jsp").forward(request, response);
		
	}
	
	/**
	 * 用户道具购买查询
	 * @param request
	 * @param response
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryPurchase(HttpServletRequest request, HttpServletResponse response, String userId, int pageNo, int pageSize)
		throws ServletException, IOException {
		User loginUser = (User)request.getSession().getAttribute("user");
		IUserDataService userDataService = new UserDataServiceImpl();
		List<Product>  productList = userDataService.queryAllProduct(loginUser.getProviderID());
		String productIds = "";
		if(loginUser.getProviderID()!=1){
			for(Product p:productList){
				productIds += p.getProductId()+",";
			}
			productIds = productIds.substring(0, productIds.length()-1);
		}
		IUserRecordService userRecordService = new UserRecordServiceImpl();
		PageModel<UserSubscribeRecord> userPurchaseModel = userRecordService.queryUserPurchaseByUserId(loginUser.getProviderID(),productIds,userId, pageNo, pageSize);
		request.setAttribute("userPurchaseModel", userPurchaseModel);
		request.setAttribute("userId", userId);
		request.getRequestDispatcher("/web/userQuery/userPurchase.jsp").forward(request, response);
	
	}

}
