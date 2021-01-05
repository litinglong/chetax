package com.silva.chetax.auth.config;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RedisConnectionFactory redisConnectionFactory;

	@Autowired(required = true)
	@Qualifier("chetaxUserDetailsService")
	public UserDetailsService userDetailsService;

	@Resource
	private DataSource dataSource;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		configureWithMemory(clients);
		configureWithJdbc(clients);
	}

	private void configureWithMemory(ClientDetailsServiceConfigurer clients) throws Exception {
		String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
//		String finalSecret = "{bcrypt}" + passwordEncoder.encode("123456");

		// 配置两个客户端，一个用于password认证一个用于client认证
		clients.inMemory().withClient("client_1").resourceIds(Utils.RESOURCEIDS.ORDER)
				.authorizedGrantTypes("client_credentials", "refresh_token", "authorization_code").scopes("select")
				.authorities("oauth2").secret(finalSecret).and().withClient("client_2")
				.resourceIds(Utils.RESOURCEIDS.ORDER)
				.authorizedGrantTypes("password", "refresh_token", "authorization_code").scopes("server")
				.authorities("oauth2").secret(finalSecret).and().withClient("client_3")
				.resourceIds(Utils.RESOURCEIDS.ORDER).secret(finalSecret).authorizedGrantTypes("authorization_code")
				.scopes("app").redirectUris("http://www.baidu.com").and().withClient("client_4")
				.resourceIds(Utils.RESOURCEIDS.ORDER).secret(finalSecret).authorizedGrantTypes("implicit")
				.scopes("test1").redirectUris("http://www.baidu.com");
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

	private void configureWithJdbc(ClientDetailsServiceConfigurer clients) throws Exception {

		// 默认值InMemoryTokenStore对于单个服务器是完全正常的（即，在发生故障的情况下，低流量和热备份备份服务器）。大多数项目可以从这里开始，也可以在开发模式下运行，以便轻松启动没有依赖关系的服务器。
		// 这JdbcTokenStore是同一件事的JDBC版本，它将令牌数据存储在关系数据库中。如果您可以在服务器之间共享数据库，则可以使用JDBC版本，如果只有一个，则扩展同一服务器的实例，或者如果有多个组件，则授权和资源服务器。要使用JdbcTokenStore你需要“spring-jdbc”的类路径。
		// 这个地方指的是从jdbc查出数据来存储
		clients.withClientDetails(clientDetails());
	}

	@Bean
	public ClientDetailsService clientDetails() {
		return new JdbcClientDetailsService(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		configureWithJwt(endpoints);
	}

	public void configureWithRadis(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(new ChetaxRedisTokenStore(redisConnectionFactory))
				.authenticationManager(authenticationManager)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.userDetailsService(userDetailsService);
	}

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	public void configureWithJwt(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(
				Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter));
		endpoints.tokenStore(tokenStore)
				 .tokenEnhancer(tokenEnhancerChain)
				.authenticationManager(authenticationManager)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.userDetailsService(userDetailsService)
				// refresh_token有两种使用方式：重复使用(true)、非重复使用(false)，默认为true
				// 1.重复使用：access_token过期刷新时， refresh token过期时间未改变，仍以初次生成的时间为准
				// 2.非重复使用：access_token过期刷新时， refresh_token过期时间延续，在refresh_token有效期内刷新而无需失效再次登录
				.reuseRefreshTokens(false);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// 允许表单认证
		security.allowFormAuthenticationForClients();
		// 开启/oauth/token_key验证端口无权限访问
		security.tokenKeyAccess("permitAll()");
		// 开启/oauth/check_token验证端口认证权限访问
		security.checkTokenAccess("isAuthenticated()");
	}

	/**
	 * 使用非对称加密算法对token签名
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(keyPair());
		return converter;
	}

	/**
	 * 从classpath下的密钥库中获取密钥对(公钥+私钥)
	 */
	@Bean
	public KeyPair keyPair() {
		KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource("chetax.jks"),
				"123456".toCharArray());
		KeyPair keyPair = factory.getKeyPair("chetax", "123456".toCharArray());
		return keyPair;
	}

	/**
	 * JWT内容增强
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return (accessToken, authentication) -> {
			Map<String, Object> map = new HashMap<>(1);
//			User user = (User) authentication.getUserAuthentication().getPrincipal();
			Map<String, Object> custumer = new HashMap<>(1);
			map.put("user_info", custumer);
			custumer.put("userName", authentication.getName());
			custumer.put("telephone", "13349935750");
			custumer.put("email", "328172957@qq.com");
//			map.put("JWT_USER_ID_KEY", "test1");
//			map.put("JWT_CLIENT_ID_KEY", "test2");
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
			return accessToken;
		};
	}
}
