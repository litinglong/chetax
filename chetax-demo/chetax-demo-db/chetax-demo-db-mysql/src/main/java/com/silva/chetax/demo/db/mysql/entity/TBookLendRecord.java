package com.silva.chetax.demo.db.mysql.entity;
import java.util.List;
import lombok.Data;
@Data
public class TBookLendRecord {
	private	String id;
	private	String bookName;
	private	String lastLendtime;
	private	List<TUser> userList;
}
