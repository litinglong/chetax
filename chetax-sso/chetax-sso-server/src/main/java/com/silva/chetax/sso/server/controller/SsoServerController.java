package com.silva.chetax.sso.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import com.silva.chetax.sso.server.utils.SsoCache;

@Controller
public class SsoServerController {

    Logger logger = LoggerFactory.getLogger(SsoServerController.class);
    

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 去到登录页面
     *
     * @param model     model
     * @param returnUrl 登录成功后需要返回到的url
     * @param request   请求
     * @return java.lang.String
     * @date 2020/9/28 15:22
     * @since 1.0.0
     */
    @RequestMapping(value = "/toLoginView", method = {RequestMethod.GET, RequestMethod.POST})
    public String toLoginView(Model model, String returnUrl, HttpServletRequest request) {
    	logger.info("/toLoginView的sessionId：{}", request.getSession().getId());
        model.addAttribute("returnUrl", returnUrl);
        // 返回到登录页面
        return "login";
    }

    /**
     * 实际上不会运行该方法中的内容，因为会被拦截器给拦截了跳转到上面的/toLoginView接口。或许可以不写?
     *
     * @param returnUrl 返回地址
     * @return java.lang.String
     * @date 2020/9/28 15:23
     * @since 1.0.0
     */
    @RequestMapping("/toLogin")
    @ResponseBody
    public String toLogin(String returnUrl) {
        System.out.println("toLogin");
        return "success";
    }

    /**
     * 实际登录，验证用户名、密码的接口
     *
     * @param username  用户名
     * @param password  密码
     * @param returnUrl 返回的url
     * @param request   请求
     * @param response  响应
     * @return java.lang.String
     * @date 2020/9/28 15:25
     * @since 1.0.0
     */
    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password, String returnUrl, HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        checkUser(username, password);
        // 用户名密码正确，设置浏览器和sso-server会话成功
        request.getSession().setAttribute("isLogin", true);
        String sessionId = request.getSession().getId();
        logger.info("/login中的sessionId：{}", sessionId);
        // 生成授权令牌，然后带着令牌跳转回最初的请求地址
        String token = UUID.randomUUID().toString();
        SsoCache.tokenMap.put(token, sessionId);
        SsoCache.sessionTokenMap.put(sessionId, token);
        if (!StringUtils.isEmpty(returnUrl)) {
            if (returnUrl.contains("?")) {
                returnUrl = returnUrl + "&token=" + token;
            } else {
                returnUrl = returnUrl + "?token=" + token;
            }
            // 重定向到要返回的url
            response.sendRedirect(returnUrl);
        }
        return "success";
    }

    /**
     * 验证token
     *
     * @param token 令牌
     * @param systemUrl 要返回的url
     * @param request 请求
     * @return java.lang.String
     * @date 2020/9/28 15:29
     * @since 1.0.0
     */
    @GetMapping("/verifyToken")
    @ResponseBody
    public String verifyToken(String token, String systemUrl, HttpServletRequest request) {
    	logger.info("/verifyToken中的sessionId：{}", request.getSession().getId());
        // 看sso-server中是否存在该token，存在说明登录了
        String sessionId = SsoCache.tokenMap.get(token);
        if (!StringUtils.isEmpty(sessionId)) {
            // 注册系统
            synchronized (token.intern()) {
                // TODO 应该用个set存储系统退出地址，防止重复验证
                List<String> systemUrlList = SsoCache.tokenSystemUrlMap.get(token);
                if (systemUrlList == null) {
                    systemUrlList = new ArrayList<>();
                }
                systemUrlList.add(systemUrl);
                SsoCache.tokenSystemUrlMap.put(token, systemUrlList);
            }
        }
        return sessionId;
    }

    /**
     * 退出
     *
     * @param token 令牌
     * @param request 请求
     * @param response 响应
     * @return java.lang.String
     * @date 2020/9/28 15:31
     * @since 1.0.0
     */
    @RequestMapping("/logout")
    public String logout(String token, HttpServletRequest request, HttpServletResponse response) throws
            InterruptedException, IOException {
        String returnUrl = request.getParameter("returnUrl");
        // 根据token进行退出
        // 退出sso-server
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        session.invalidate();
        if (StringUtils.isEmpty(token)) {
            // token为空说明是直接访问的sso-server退出，那么需要根据sessionId拿到对应的token进行子系统退出
            token = SsoCache.sessionTokenMap.get(sessionId);
        }
        // 退出子系统
        List<String> systemUrlList = SsoCache.tokenSystemUrlMap.get(token);
        if (systemUrlList != null && systemUrlList.size() > 0) {
            // 之前学过CountDownLatch，这里顺便结合下parallel()使用一下。不知道能不能达到想要的效果
            CountDownLatch latch = new CountDownLatch(systemUrlList.size());
            String t = token;
            systemUrlList.stream().parallel().forEach(systemUrl -> {
                restTemplate.getForEntity(systemUrl + "?token=" + t, String.class);
                latch.countDown();
            });
            latch.await();
            // 子系统都退出后，移除用户的session、token等信息
            SsoCache.tokenMap.remove(token);
            SsoCache.tokenSystemUrlMap.remove(token);
            SsoCache.sessionTokenMap.remove(sessionId);
        }
        if (!StringUtils.isEmpty(returnUrl)) {
            response.sendRedirect(returnUrl);
        }
        // 退出后返回到登录页
        return "login";
    }


    // 验证用户名和密码
    private void checkUser(String username, String password) {
    }
}

