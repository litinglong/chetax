package com.silva.chetax.demo.db.mysql.entity;

/**
 * 自定义返回码
 *
 * @author wangyi
 */

public enum ErrorCode {
    /**
     * 成功
     */
    OK("0000", "success"),
    ERROR("5000", "error"),
    FAIL("4000", "fail"),
    DATA_IS_NULL("1000", "data_is_null"),
    PARAM_LEGAL("4001","param_legal");

    private String code;
    private String message;

    ErrorCode() {
    }

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCode getResultEnumByCode(String code) {
        for (ErrorCode type : ErrorCode.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return ERROR;
    }

    public static ErrorCode getResultEnumByMsg(String message) {
        for (ErrorCode type : ErrorCode.values()) {
            if (type.getMessage().equals(message)) {
                return type;
            }
        }
        return ERROR;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
