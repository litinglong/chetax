package com.ltl.matrix.sso.server.event;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@WebServlet
@WebListener
public class LogoutListener implements HttpSessionListener{
	Logger logger = LoggerFactory.getLogger(LogoutListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent event) {
    	logger.info("sessionCreated");
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        //通过httpClient向所有注册系统发送注销请求
    	logger.info("sessionDestroyed");
    }
}
