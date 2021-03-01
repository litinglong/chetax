package com.silva.chetax.demo.db.mysql.entity;
import lombok.Data;
@Data
public class TUser {
	private	String id;
	private	String userName;
	private	String sex;
	private	TBookLendRecord bookLendRecord;
}
