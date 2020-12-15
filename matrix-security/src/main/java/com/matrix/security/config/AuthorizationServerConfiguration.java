package com.matrix.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RedisConnectionFactory redisConnectionFactory;
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");

		// 配置两个客户端，一个用于password认证一个用于client认证
		clients.inMemory().withClient("client_1").resourceIds(Utils.RESOURCEIDS.ORDER)
				.authorizedGrantTypes("client_credentials", "refresh_token", "code").scopes("select").authorities("oauth2")
				.secret(finalSecret).and().withClient("client_2").resourceIds(Utils.RESOURCEIDS.ORDER)
				.authorizedGrantTypes("password", "refresh_token", "authorization_code").scopes("server").authorities("oauth2")
				.secret(finalSecret).and().withClient("client_3") // client_id
				.secret(finalSecret) // client_secret
				.authorizedGrantTypes("authorization_code") // 该client允许的授权类型
				.scopes("app");
//		String[] permissions = "ADMIN2,ROLE_ADMIN".split(",");
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		for (String permission : permissions) {
//		    authorities.add(new SimpleGrantedAuthority(permission));
//		}
//		userDetails.setAuthorities(authorities);
//		@PreAuthorize("hasRole('ADMIN')")                //允许
//		@PreAuthorize("hasRole('ROLE_ADMIN')")           //允许
//		@PreAuthorize("hasRole('ADMIN2')")               //不允许
//		@PreAuthorize("hasRole('ROLE_ADMIN2')")          //不允许
//		@PreAuthorize("hasAuthority('ADMIN2')")          //允许
//		@PreAuthorize("hasAuthority('ROLE_ADMIN2')")     //不允许
//		@PreAuthorize("hasAuthority('ADMIN')")           //不允许
//		@PreAuthorize("hasAuthority('ROLE_ADMIN')")      //允许
//		https://blog.csdn.net/qq_26878363/article/details/103632459?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromBaidu-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromBaidu-1.control
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(new MyRedisTokenStore(redisConnectionFactory)).authenticationManager(authenticationManager)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// 允许表单认证
		security.allowFormAuthenticationForClients();
	}
}
