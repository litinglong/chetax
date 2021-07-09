package com.silva.chetax.schedule.center.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author litinglong
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_schedule_result")
public class SysScheduleResultEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 关联的调度计划主键
     */
    private Long sysScheduleInfoId;

    /**
     * 服务URL
     */
    private String url;

    /**
     * 输入参数
     */
    private String requestBody;

    /**
     * 异常信息
     */
    private String exceptionMsg;

    /**
     * 结果信息
     */
    private String resultMsg;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // @DataFormAT主要是前后到后台的时间格式的转换
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8") // @JsonFormat主要是后台到前台的时间格式的转换
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private LocalDateTime endTime;
    
    @ApiModelProperty(value = "任务耗时（毫秒数）")
    private Long usedTime;
    
    @ApiModelProperty(value = "任务耗时(格式化后)")
    private String usedTimeFormated;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;



}
