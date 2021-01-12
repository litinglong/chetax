package com.silva.chetax.demo.db.mysql.entity;


import java.io.Serializable;

/**
 * @author wangyi
 */

public class ResultBody<T> implements Serializable {
    private static final long serialVersionUID = -6190689122701100762L;
    private String code = ErrorCode.OK.getCode();
    private String message;
    private T data;
    public boolean isOk() {
        return ErrorCode.OK.getCode().equals(this.code);
    }
    public boolean isEmpty() {
        return ErrorCode.DATA_IS_NULL.getCode().equals(this.code);
    }

    public static ResultBody ok() {
        return new ResultBody().code(ErrorCode.OK.getCode()).msg(ErrorCode.OK.getMessage());
    }
    public static ResultBody empty() {
        return new ResultBody().code(ErrorCode.DATA_IS_NULL.getCode()).msg(ErrorCode.DATA_IS_NULL.getMessage());
    }

    public static ResultBody failed() {
        return new ResultBody().code(ErrorCode.FAIL.getCode()).msg(ErrorCode.FAIL.getMessage());
    }

    public ResultBody code(String code) {
        this.code = code;
        return this;
    }

    public ResultBody msg(String message) {
        this.message = message;
        return this;
    }

    public ResultBody data(T data) {
        this.data = data;
        return this;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(T data) {
        this.data = data;
    }
}
