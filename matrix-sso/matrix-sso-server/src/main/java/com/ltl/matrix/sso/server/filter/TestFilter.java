package com.ltl.matrix.sso.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ltl.matrix.sso.server.utils.ServletUtil;

//配置拦截路径
@WebFilter(filterName = "testFilter",urlPatterns = {"/*"})
public class TestFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(TestFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //response.setStatus(HttpStatus.UNAUTHORIZED.value());
        System.out.println("TestFilter,"+ServletUtil.getServerPath(request)+request.getRequestURI());
        System.out.println("TestFilter,"+ServletUtil.getServerPath(request)+request.getRequestURL());
        //response.sendRedirect("http://www.baidu.com");
        //执行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
