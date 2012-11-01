package cn.oyeah.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import cn.oyeah.domain.User;
import cn.oyeah.service.IUserService;
import cn.oyeah.service.impl.UserServiceImpl;

public class UserLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4787417492624867284L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	        this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String passWord = request.getParameter("passWord");
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(passWord) ){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		IUserService userService = UserServiceImpl.getInstance();
		User user = userService.loginValidate(name,passWord);
		if (user == null) {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} else {
			userService.updateLoginTime(name);//更改登入时间
			request.getSession().setAttribute("authority",user.getAuthority()); 
			request.getSession().setAttribute("name", name);
			response.sendRedirect(request.getContextPath()+"/web/admin/main.jsp");
	    }
	}

}
