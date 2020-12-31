package com.silva.chetax.sso.client.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//配置拦截路径
@WebFilter(filterName = "loginFilter", urlPatterns = { "/auth" })
public class LoginFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		Boolean isLogin = (Boolean) session.getAttribute("isLogin");
		if (isLogin == true) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		String token = null;
		Cookie cookies[] = request.getCookies();// 获取request中cookie集合
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if("".equals(name)) {
				token=cookie.getValue();
			}
		}
		String url = "http://127.0.0.1:9898/loginPage?redirectUrl=" + request.getRequestURL();
		if(token!=null&& !token.equals("")) {
			url = url+ "&token="+token;
		}
		response.sendRedirect("http://127.0.0.1:9898/loginPage?redirectUrl=" + request.getRequestURL());
		// response.setStatus(HttpStatus.UNAUTHORIZED.value());
		// response.sendRedirect("http://www.baidu.com");
		// 执行

	}

	@Override
	public void destroy() {

	}
}
