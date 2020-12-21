package com.ltl.gateway.config;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * Token中缓存用户信息
 * @author 大仙
 */
@Data
public class BaseUser implements Serializable {
    /**
     * 主键Id
     */
    protected Long id;

    /**
     * 数据创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createDate = LocalDateTime.now();
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 邮箱，用户企业人员进行登录
     */
    private String email;
    /**
     * 电话号码，用户客户登录
     */
    private String telephone;
    /**
     * 头像
     */
    private String headerUrl;

}
