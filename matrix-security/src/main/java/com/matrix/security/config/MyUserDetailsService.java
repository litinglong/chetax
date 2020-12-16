package com.matrix.security.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component(value = "myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	logger.info("usernameis:" + username);
        // 查询数据库操作
        if(!username.equals("admin")){
            throw new UsernameNotFoundException("用户不存在");
        }else{
            // 用户角色也应在数据库中获取
            String role = "ROLE_ADMIN";
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
            // 线上环境应该通过用户名查询数据库获取加密后的密码
            String password = passwordEncoder.encode("123456");
            // 返回默认的 User
            // return new org.springframework.security.core.userdetails.User(username,password, authorities);

            // 返回自定义的 KiteUserDetails
            User user = new User(username,password,authorities);
            return user;
        }
    }


}
