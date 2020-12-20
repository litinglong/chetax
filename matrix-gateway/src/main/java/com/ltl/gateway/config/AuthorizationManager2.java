package com.ltl.gateway.config;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.xmlunit.util.Convert;

import reactor.core.publisher.Mono;

/**
 * 鉴权管理器
 * https://blog.csdn.net/zhuwei_clark/article/details/104538792
 */
@Component
public class AuthorizationManager2 implements ReactiveAuthorizationManager<AuthorizationContext> {
//    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        //从Redis中获取当前路径可访问角色列表
//        URI uri = authorizationContext.getExchange().getRequest().getURI();
//        Object obj = redisTemplate.opsForHash().get(RedisConstant.RESOURCE_ROLES_MAP, uri.getPath());
//    	List<String> authorities = Convert.toList(String.class,obj);
        List<String> authorities = new ArrayList<String>(){{add("ROLE_ADMIN");add("ROLE_TEST");}} ;
//        authorities = authorities.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        //认证通过且角色匹配的用户可访问当前路径
//        return mono
//                .filter(Authentication::isAuthenticated)
//                .flatMapIterable(Authentication::getAuthorities)
//                .map(GrantedAuthority::getAuthority)
//                .any(authorities::contains)
//                .map(AuthorizationDecision::new)
//                .defaultIfEmpty(new AuthorizationDecision(false));
        
//        return Mono.just(new AuthorizationDecision(true));
        
        return mono.map(a ->  {
            if(a.isAuthenticated()){
                return new AuthorizationDecision(true);
            }else{
                return new AuthorizationDecision(false);
            }
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }

}
