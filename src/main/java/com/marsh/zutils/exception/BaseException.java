package com.marsh.zutils.exception;

import lombok.Getter;

/**
 * 内部异常
 *
 * @author lucky.zhou
 */
public class BaseException extends RuntimeException {

    @Getter
    private String msg;

    public BaseException() {
        super();
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(String code, String msg) {
        super();
        this.msg = msg;
    }
}
