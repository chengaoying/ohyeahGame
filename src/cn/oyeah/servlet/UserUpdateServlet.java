package cn.oyeah.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oyeah.domain.User;
import cn.oyeah.service.IUserService;
import cn.oyeah.service.impl.UserServiceImpl;

public class UserUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3485345312433284226L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setPassWord(password);
		u.setRole(role);
		if(role.equalsIgnoreCase("admin")){
			u.setAuthority(3);
		} else if(role.equalsIgnoreCase("worker")){
			u.setAuthority(2);
		} else if (role.equalsIgnoreCase("partner")){
			u.setAuthority(1);
		} else {
			return;
		}
		
		IUserService userService = UserServiceImpl.getInstance();
		userService.updateUser(u);
		request.setAttribute("msg", "修改成功!");
		
		request.getRequestDispatcher("/web/admin/success.jsp").forward(request, response);
		
	}

}
