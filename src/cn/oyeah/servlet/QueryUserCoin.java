package cn.oyeah.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oyeah.service.IUserService;
import cn.oyeah.service.impl.UserServiceImpl;

public class QueryUserCoin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IUserService userService = UserServiceImpl.getInstance();
		Map<String, Integer> map = userService.queryCoin();
		List<Integer> rows = userService.getTableRows();
		Map<String, Integer> userIds = new HashMap<String,Integer>();
		for(Iterator it = rows.iterator();it.hasNext();){
			int accountId = (Integer) it.next();
			String userId = userService.getUserIdByAccountId(accountId);
			userIds.put(userId, accountId);
			//System.out.println("accountId:"+accountId+"====userId:"+userId);
		}
		
		List<String> columns = userService.getTableColumns();
		Map<String, String> productNames = new HashMap<String,String>();
		
		for(Iterator it = columns.iterator();it.hasNext();){
			String productId = (String) it.next();
			String productName = userService.getGameNameByProductId(Integer.parseInt(productId));
			productNames.put(productId, productName);
			//System.out.println("productId:"+productId+"====productName:"+productName);
		}

		int amount = userService.queryAllAmount();
		request.setAttribute("map", map);
		request.setAttribute("rows", userIds);
		request.setAttribute("columns", productNames);
		request.setAttribute("amount", amount);
		request.getRequestDispatcher("/web/admin/queryCoin.jsp").forward(request, response);
		
	}

}
