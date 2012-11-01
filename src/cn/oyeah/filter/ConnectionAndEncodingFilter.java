package cn.oyeah.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.oyeah.req.ConnectionManager;

/**
 * 采用Filter统一处理字符集
 * @author Administrator
 *
 */
public class ConnectionAndEncodingFilter implements Filter {
	
	private String endcoding; 
	
	public void destroy() {
		System.out.println("destroy....");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//System.out.println("CharsetEncodingFilter--->>>begin");
		//设置字符集
		request.setCharacterEncoding(endcoding);
		
		//继续执行
		try {
			chain.doFilter(request, response);
		} finally{
			ConnectionManager.remove();
		}
		
		//System.out.println("CharsetEncodingFilter--->>>end");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.endcoding = filterConfig.getInitParameter("encoding");
		System.out.println("CharsetEncodingFilter.init()-->> endcoding=" + endcoding);
	}

}
