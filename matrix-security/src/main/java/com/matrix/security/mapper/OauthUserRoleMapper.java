package com.matrix.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface OauthUserRoleMapper{
	  @Select("SELECT b.name as role FROM oauth_user_role a,oauth_role b WHERE a.user_id = #{userId} AND a.role_id = b.id")
	  List<SimpleGrantedAuthority> findByUserId(@Param("userId") Integer userId);
}
