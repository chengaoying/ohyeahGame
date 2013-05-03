package cn.oyeah.servlet;

import java.io.IOException;
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
		List<String> rows = userService.getTableRows();
		Map<String, String> columns = userService.getTableColumns();
		int amount = userService.queryAllAmount();
		request.setAttribute("map", map);
		request.setAttribute("rows", rows);
		request.setAttribute("columns", columns);
		request.setAttribute("amount", amount);
		request.getRequestDispatcher("/web/admin/queryCoin.jsp").forward(request, response);
		
	}

}
