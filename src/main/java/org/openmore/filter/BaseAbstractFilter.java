package org.openmore.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class BaseAbstractFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		// 获得方法
		String method = request.getMethod();
		// 获得URI
		String requestUri = request.getRequestURI();

		String contextPath = request.getContextPath();
		// 获得URL
		String url = requestUri.substring(contextPath.length());

/*		System.out.println("requestUri: " + requestUri);
		System.out.println("contextPath: " + contextPath);
		System.out.println("url: " + url);*/
		
		doFilter(request, response, chain, session, method, url);
	}

	/**
	 * 
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @param session
	 *            session 可能为空
	 * @param method
	 *            method
	 * @param url
	 *            url
	 */
	public abstract void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			HttpSession session, String method, String url)
			throws IOException, ServletException;
}
