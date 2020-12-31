package com.silva.gateway.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import reactor.core.publisher.Mono;

/**
 * 鉴权管理器
 * https://blog.csdn.net/zhuwei_clark/article/details/104538792
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
//    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;
	private static Logger log = LoggerFactory.getLogger(AuthorizationManager.class);
    /**
     * 构造对象
     */
    @Data
    class AuthUser{
        private String authority;

        private BaseUser baseUser;
    }
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
//        mono.filter(a ->  a.isAuthenticated()).flatMapIterable(a->{
//        	Object o1= a.getPrincipal();
//			a.getAuthorities();
//			Object o2= a.getCredentials();
//			Object o3= a.getDetails();
//			String name =a.getName();
//			System.out.println(name);
//		});
//        return mono.map(a ->  {
//            if(a.isAuthenticated()){
//                return new AuthorizationDecision(true);
//            }else{
//                return new AuthorizationDecision(false);
//            }
//        }).defaultIfEmpty(new AuthorizationDecision(false));
        
        ServerHttpRequest request =  authorizationContext.getExchange().getRequest();
        //需要进行权限验证的
        return
        		//过滤验证成功的
        		mono.filter(a ->  a.isAuthenticated())
        		
                        //转换成Flux
                    .flatMapIterable(a -> {
                        Jwt jwtValue = null;
                        if(a.getPrincipal() instanceof Jwt){
                            jwtValue = (Jwt)a.getPrincipal();
                        }
                        JSONObject tokenInfo = JSONObject.parseObject(JSONObject.toJSONString(jwtValue.getClaims()));
                        BaseUser baseUser = tokenInfo.getJSONObject(Constant.USER_INFO).toJavaObject(BaseUser.class);
                        //存储当前数据
                        List<AuthUser> authUsers = new ArrayList<>();
                        JSONArray array = tokenInfo.getJSONArray("authorities");
                        for (int i = 0;i<array.size();i++){
                            AuthUser authUser = new AuthUser();
                            authUser.setBaseUser(baseUser);
                            authUser.setAuthority(array.get(i).toString());
                            authUsers.add(authUser);
                        }
                        return authUsers;
                    })
                     //转成成权限名称
                    .any(c-> {//检测权限是否匹配
                        //获取当前用户
                        BaseUser baseUser = c.getBaseUser();
                        //判断当前携带的Token是否有效
                        String  token = request.getHeaders().getFirst(Constant.AUTHORIZATION).replace("Bearer ","");
//                        if(!TokenUtil.judgeTokenValid(String.valueOf(baseUser.getId()),redisTemplate,token)){
//                        	return false;
//                        }
                        
                        if(token==null||"".equals(token)){
                        	return false;
                        }
                        //获取当前角色
                        String authority = c.getAuthority();
                        //通过当前权限码查询可以请求的地址
                        log.debug("当前角色是：{}",authority);
//                        List<Permission> permissions = permissionUtil.getResultPermission(authority);
//                        permissions = permissions.stream().filter(permission -> StringUtils.isNotBlank(permission.getRequestUrl())).collect(Collectors.toList());
//                        //请求URl匹配，放行
//                        if(permissions.stream().anyMatch(permission -> matcher.match(permission.getRequestUrl(),url))){
//                            return true;
//                        }
                        if(authority==null&&"".equals(authority)) {
                        	return true;
                        }
                        return false;
                    })
                    .map(hasAuthority ->  new AuthorizationDecision(hasAuthority)).defaultIfEmpty(new AuthorizationDecision(false));
    }

}
