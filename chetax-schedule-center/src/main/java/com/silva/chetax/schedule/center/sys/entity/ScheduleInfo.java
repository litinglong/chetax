package com.silva.chetax.schedule.center.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_schedule_info")
@AllArgsConstructor
public class ScheduleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private BigDecimal id;
    /**
     * 服务url
     */
    private String url;

    /**
     * 请求体
     */
    @TableField("request_body")
    private String requestbody;

    /**
     * 周期表达式
     */
    private String cron;

    /**
     * 任务状态
     */
    private Integer status;

    private String createTime;

    private String createUser;

    private String updateTime;

    private String updateUser;
    private String groupName;
    private String jobName;
    private String description;
    private Integer concurrentTag;
}
