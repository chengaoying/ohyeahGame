package cn.oyeah.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest)request;
			if(req.getSession(false) != null && (String)req.getSession().getAttribute("name") != null) {
				chain.doFilter(request, response);
			} else {
				HttpServletResponse res = (HttpServletResponse)response;
				res.sendRedirect(req.getContextPath()+"/login.jsp");
			}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
