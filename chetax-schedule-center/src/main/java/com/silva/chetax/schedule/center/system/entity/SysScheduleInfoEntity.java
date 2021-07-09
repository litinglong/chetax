package com.silva.chetax.schedule.center.system.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author litinglong
 * @since 2021-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_schedule_info")
@AllArgsConstructor
public class SysScheduleInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    private String groupName;
    private String jobName;
    private String description;
    
    /**
     * 服务url
     */
    @Size(max = 10, message = "zuidawei10")
    private String url;

    /**
     * 请求体
     */
    @TableField("request_body")
    private String requestBody;

    /**
     * 周期表达式
     */
    private String cron;
    
    private Integer concurrentTag;

    /**
     * 任务状态
     */
    private Integer status;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;
    
}
