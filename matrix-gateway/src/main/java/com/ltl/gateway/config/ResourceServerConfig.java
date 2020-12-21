package com.ltl.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
//import cn.hutool.core.util.ArrayUtil;
//import cn.hutool.json.JSONUtil;
//import com.youlai.common.core.constant.AuthConstants;
//import com.youlai.common.core.result.Result;
//import com.youlai.common.core.result.ResultCode;
//import com.youlai.gateway.security.AuthorizationManager;
//import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

//import reactor.core.publisher.Mono;

//@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig{

	@Autowired
    AuthorizationManager authorizationManager2;
//    private final IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    CustomServerAccessDeniedHandler customServerAccessDeniedHandler;
    @Autowired
    CustomServerAuthenticationEntryPoint customServerAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        http.authorizeExchange()
                .pathMatchers("/actuator/**","/matrix-security/oauth/token").permitAll()//白名单配置
                .anyExchange().access(authorizationManager2)//鉴权管理器配置
                .and().exceptionHandling()
                .accessDeniedHandler(customServerAccessDeniedHandler)//处理未授权
                .authenticationEntryPoint(customServerAuthenticationEntryPoint)//处理未认证
                .and().csrf().disable();
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
//        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITY_CLAIM_NAME);
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("AUTHORITY_PREFIX");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("AUTHORITY_CLAIM_NAME");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
