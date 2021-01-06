package com.silva.chetax.demo.db.mongodb.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    private Long id;
    private String userName;
    private String passWord;
}
