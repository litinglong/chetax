package com.silva.chetax.schedule.center.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class SysScheduleResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    private String createTime;

    private String createUser;

    private String updateTime;

    private String updateUser;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 关联的调度计划主键
     */
    private Long sysScheduleInfoId;


}
