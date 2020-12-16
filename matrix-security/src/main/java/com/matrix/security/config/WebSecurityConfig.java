package com.matrix.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//		String finalPassword = "{bcrypt}" + bCryptPasswordEncoder.encode("123456");
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
//		manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
//		return manager;
//	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatchers()
//        .antMatchers("/login", "/oauth/**")
//        .and()
//        .authorizeRequests()
//        .anyRequest()
//        .authenticated()
//        .and()
//        .formLogin()
//        .permitAll()
//        .and().csrf().disable();
		
//		http.requestMatchers()
//		.anyRequest().and().authorizeRequests().antMatchers("/login","/oauth/**").permitAll()
//		.and()
//        .formLogin().permitAll()
//        .and()
        http.csrf().disable();
        //打开表单登录；
        //web应用或第三方授权时，允许使用用户名密码登录
        http.formLogin();
        //oauth相关接口不做权限校验
        http.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/token");
        //其他接口需要鉴权
        http.authorizeRequests().anyRequest().authenticated();
	}
}
