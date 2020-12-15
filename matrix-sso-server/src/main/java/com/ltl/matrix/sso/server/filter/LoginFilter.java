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
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

import com.ltl.matrix.sso.server.utils.SsoCache;

//配置拦截路径
@WebFilter(filterName = "loginFilter",urlPatterns = {"/*"})
public class LoginFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        logger.info("认证中心的doFilter中sessionId：{}", session.getId());
        Object obj = session.getAttribute("isLogin");
        String returnUrl = request.getParameter("returnUrl");
        if (obj != null && ((boolean) obj)) {
            // 已经登录了的话，就将token返回
            if (returnUrl.contains("?")) {
                returnUrl = returnUrl + "&token=" + SsoCache.sessionTokenMap.get(session.getId());
            } else {
                returnUrl = returnUrl + "?token=" + SsoCache.sessionTokenMap.get(session.getId());
            }
            response.sendRedirect(returnUrl);
            return;
        }
        // 没有登录，重定向到登录页面
        String toLoginUrl = "/toLoginView";
        if (!StringUtils.isEmpty(returnUrl)) {
            toLoginUrl = toLoginUrl + "?returnUrl=" + returnUrl;
        }
        response.sendRedirect(toLoginUrl);
    }

    @Override
    public void destroy() {

    }
}
