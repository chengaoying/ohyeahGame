package cn.oyeah.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oyeah.service.IUserService;
import cn.oyeah.service.impl.UserServiceImpl;
import cn.oyeah.util.PageModel;

public class UserQueryList extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 593697834758636488L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IUserService userService = UserServiceImpl.getInstance();
		String pNo = request.getParameter("pageNo");
		int pageNo ;
		if(pNo == null){
			pageNo = 1;
		} else {
			pageNo = Integer.parseInt(pNo);
		}
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		//System.out.println("pageSize" + pageSize);
		PageModel pageModel = userService.queryUserList(pageNo, pageSize);
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("/web/admin/admin_list.jsp").forward(request, response);
	}

}
