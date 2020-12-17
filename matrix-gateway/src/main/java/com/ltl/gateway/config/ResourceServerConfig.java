package com.ltl.gateway.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;

import io.netty.handler.codec.http.HttpContentEncoder.Result;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)https://www.cnblogs.com/haoxianrui/p/13719356.html
public class ResourceServerConfig{

    private AuthorizationManager authorizationManager;
    private WhiteListConfig whiteListConfig;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http.oauth2ResourceServer().authenticationEntryPoint(authenticationEntryPoint());
		;
		http.authorizeExchange()
		        .pathMatchers((String[])whiteListConfig.getUrls().toArray()).permitAll()
		        .anyExchange().access(authorizationManager)
		        .and()
		        .exceptionHandling()
		        .accessDeniedHandler(accessDeniedHandler()) // 处理未授权
		        .authenticationEntryPoint(authenticationEntryPoint()) //处理未认证
		        .and().csrf().disable();
		
		return http.build();
    }


    /**
     * 未授权
     *
     * @return
     */
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, denied) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> {
                        response.setStatusCode(HttpStatus.OK);
                        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                        response.getHeaders().set("Access-Control-Allow-Origin", "*");
                        response.getHeaders().set("Cache-Control", "no-cache");
//                        String body = JSONUtil.toJsonStr(Result.failed(ResultCode.USER_ACCESS_UNAUTHORIZED));
                        String body = "USER_ACCESS_UNAUTHORIZED";
                        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
                        return response.writeWith(Mono.just(buffer))
                                .doOnError(error -> DataBufferUtils.release(buffer));
                    });

            return mono;
        };
    }

    /**
     * token无效或者已过期自定义响应
     */
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, e) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> {
                        response.setStatusCode(HttpStatus.OK);
                        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                        response.getHeaders().set("Access-Control-Allow-Origin", "*");
                        response.getHeaders().set("Cache-Control", "no-cache");
//                        String body = JSONUtil.toJsonStr(Result.failed(ResultCode.TOKEN_INVALID_OR_EXPIRED));
                        String body = "TOKEN_INVALID_OR_EXPIRED";
                        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
                        return response.writeWith(Mono.just(buffer))
                                .doOnError(error -> DataBufferUtils.release(buffer));
                    });
            return mono;
        };
    }
}
