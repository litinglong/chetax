package com.silva.chetax.demo.spring.source.comments.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Test001Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2046251064646881442L;
	private String text;
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 将日期格式的字符串解析为Date类型
	private Date date;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 将事件格式的字符串解析为Date类型
	private Date time;
}
