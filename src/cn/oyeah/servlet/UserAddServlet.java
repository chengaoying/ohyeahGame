package cn.oyeah.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oyeah.domain.User;
import cn.oyeah.service.IUserService;
import cn.oyeah.service.impl.UserServiceImpl;

public class UserAddServlet extends HttpServlet {

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
		User user =  new User();
		user.setName(request.getParameter("name"));
		user.setPassWord(request.getParameter("password"));
		user.setRole(request.getParameter("role"));
		user.setProviderID(Integer.parseInt(request.getParameter("providerId")));
		String role = (String)request.getParameter("role");
		if(role.equalsIgnoreCase("admin")){
			user.setAuthority(3);
		} else if(role.equalsIgnoreCase("worker")){
			user.setAuthority(2);
		} else if (role.equalsIgnoreCase("partner")){
			user.setAuthority(1);
		} else {
			return;
		}
		IUserService userService = UserServiceImpl.getInstance();
		userService.addUser(user);
		request.setAttribute("msg", "添加成功!");
		request.getRequestDispatcher("/web/admin/success.jsp").forward(request, response);
		
	}

}
