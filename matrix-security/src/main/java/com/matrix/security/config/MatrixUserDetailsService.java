package com.matrix.security.config;

import java.util.List;

import javax.annotation.Resource;

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

import com.matrix.security.entity.OauthUserDetails;
import com.matrix.security.mapper.OauthUserDetailsMapper;
import com.matrix.security.mapper.OauthUserRoleMapper;

@Component(value = "myUserDetailsService")
public class MatrixUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(MatrixUserDetailsService.class);
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Resource
    OauthUserDetailsMapper oauthUserDetailsMapper;
    
    @Resource
    OauthUserRoleMapper oauthUserRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	logger.info("username:" + username);
    	OauthUserDetails oauthUserDetails = oauthUserDetailsMapper.findByUsername(username);
        // 查询数据库操作
        if(oauthUserDetails == null){
            throw new UsernameNotFoundException("用户不存在");
        }else{
        	List<SimpleGrantedAuthority> authorities = oauthUserRoleMapper.findByUserId(oauthUserDetails.getId());
            User user = new User(username,oauthUserDetails.getPassword(),authorities);
            return user;
        }
    }


}
