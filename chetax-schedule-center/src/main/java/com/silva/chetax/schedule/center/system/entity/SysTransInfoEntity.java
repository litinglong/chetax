package com.silva.chetax.schedule.center.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author litinglong
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysTransInfo对象", description="")
@TableName("sys_trans_info")
public class SysTransInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "交易号")
    private String transJnlNo;

    @ApiModelProperty(value = "交易方法")
    private String transMethod;

    @ApiModelProperty(value = "交易输入参数")
    private String transInput;

    @ApiModelProperty(value = "交易输出参数")
    private String transOutput;
    
    @ApiModelProperty(value = "交易异常")
    private String transException;

    @ApiModelProperty(value = "交易开始时间")
    private LocalDateTime transStartTime;

    @ApiModelProperty(value = "交易结束时间")
    private LocalDateTime transEndTime;

    @ApiModelProperty(value = "交易耗时（毫秒数）")
    private Long transUsedTime;
    
    @ApiModelProperty(value = "交易耗时(格式化后)")
    private String transUsedTimeFormated;
    
    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateUser;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
