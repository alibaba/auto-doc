package com.alibaba.auto.doc.exception;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/16 4:15 下午
 * @description：
 */
public class AutoDocException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public AutoDocException(String errorCode) {
        this(errorCode, "error with code: " + errorCode);
    }

    public AutoDocException(String errorCode, String message) {
        this(errorCode, message, null);
    }

    public AutoDocException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
