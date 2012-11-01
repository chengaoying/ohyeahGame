package cn.oyeah.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oyeah.domain.Product;
import cn.oyeah.domain.UserSubscribeStatistic;
import cn.oyeah.service.IUserSubscribe;
import cn.oyeah.service.impl.UserSubscribeServiceImpl;
import cn.oyeah.util.DateTimeUtils;
import cn.oyeah.util.PageModel;

/**
 * 充值用户数统计
 * @author xiaochen 2011-12-12
 *
 */
public class SubscribeStatisticServlet extends HttpServlet {


	private static final long serialVersionUID = -285418825715311180L;
	private String command = "";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		command = request.getParameter("command");
		if(startTime == null){
			startTime = DateTimeUtils.getStartTime();
		}
		if(endTime == null){
			endTime = DateTimeUtils.getEndTime();
		}
		int pageSize = 20;
		System.out.println(command);
		if(command.equals("single")){
			int productId = 0;
			if (request.getParameter("productId") != null) {
				productId = Integer.parseInt(request.getParameter("productId"));
			}
			this.queryUserSubscribeRecordByTime(request, response, pageNo, pageSize ,startTime, endTime, productId);
		} else if(command.equals("all")) {
			this.queryUserSubscribeRecord(request, response, pageNo, pageSize, startTime, endTime);
		}
	
	}
	
	/**
	 * 默认时间查询充值用户
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryUserSubscribeRecord(HttpServletRequest request, HttpServletResponse response ,int pageNo, int pageSize
			, String startTime, String endTime)throws ServletException, IOException{
		
		IUserSubscribe userSubscribe = new UserSubscribeServiceImpl();
		PageModel<UserSubscribeStatistic> pageModel = userSubscribe.queryAllUserSubscribeRecord(pageNo, pageSize);
		List<Product> productList = userSubscribe.queryAllProduct();
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("command", command);
		request.setAttribute("productList", productList);
		//System.out.println("pageMoldel:--->>"+ pageModel.getList().size());
		request.getRequestDispatcher("/web/userSubscribeRecord/userSubscribeRecord_all.jsp").forward(request, response);
	}
	
	/**
	 * 按时间条件查询充值用户数
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryUserSubscribeRecordByTime(HttpServletRequest request, HttpServletResponse response ,int pageNo, int pageSize
			,String startTime, String endTime, int productId)	throws ServletException, IOException{
		
		IUserSubscribe userSubscribe = new UserSubscribeServiceImpl();
		PageModel<UserSubscribeStatistic> pageModel = userSubscribe.queryUserSubscribeRecord(startTime, endTime, pageNo, pageSize, productId);
		List<Product> productList = userSubscribe.queryAllProduct();
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("command", command);
		request.setAttribute("productId", productId);
		request.setAttribute("productList", productList);
		//System.out.println("pageMoldel:--->>"+ pageModel.getList().size());
		request.getRequestDispatcher("/web/userSubscribeRecord/userSubscribeRecord_single.jsp").forward(request, response);
	}

}
