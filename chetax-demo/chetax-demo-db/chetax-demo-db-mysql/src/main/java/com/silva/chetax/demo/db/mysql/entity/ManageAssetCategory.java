package com.silva.chetax.demo.db.mysql.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ManageAssetCategory {
    private Long id;
    private Long parentCategoryId;
    private String categoryCode;
    private String categoryName;
    private char isDelete;
    private String createId;
    private String updateId;
    private Date createTime;
    private Date updateTime;
}
