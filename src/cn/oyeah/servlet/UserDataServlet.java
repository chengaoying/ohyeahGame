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
import cn.oyeah.domain.UserDataAnalysis;
import cn.oyeah.service.IUserDataService;
import cn.oyeah.service.impl.UserDataServiceImpl;
import cn.oyeah.util.DateTimeUtils;
import cn.oyeah.util.PageModel;

public class UserDataServlet extends HttpServlet {

	private static final long serialVersionUID = -7011320584511574277L;
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
		System.out.println("UserDataServlet command::"+command);
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = 20;
		
		if (StringUtils.equals(command, "all")){
			this.queryAllUserData(request, response, pageNo, pageSize, startTime, endTime);
		} else if(StringUtils.equals(command, "single")){
			int productId = Integer.parseInt(request.getParameter("productId"));
			this.querySingleUserData(request, response, pageNo, pageSize, startTime, endTime, productId);
		}
	}
	
	/**
	 * 查询用户所有游戏充值
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void queryAllUserData(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, String startTime, String endTime)
	throws ServletException, IOException{
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

		PageModel<UserDataAnalysis> pageModel = userDataService.queryAllUserData(loginUser.getProviderID(),productIds,startTime, endTime, pageNo, pageSize);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("sTime", sTime);
		request.setAttribute("eTime", eTime);
		request.setAttribute("command", command);
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/web/userData/userData_all.jsp").forward(request, response);
	}
	
	/**
	 * 查询用户单款游戏充值
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void querySingleUserData(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, String startTime, String endTime, int productId)
	throws ServletException, IOException{
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

		PageModel<UserDataAnalysis> pageModel = userDataService.querySingleUserData(loginUser.getProviderID(), productIds, startTime, endTime, pageNo, pageSize, productId);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("sTime", sTime);
		request.setAttribute("eTime", eTime);
		request.setAttribute("command", command);
		request.setAttribute("productId", productId);
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/web/userData/userData_single.jsp").forward(request, response);
	}

}
