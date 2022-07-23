package com.marsh.zutils.exception;

import lombok.Getter;

/**
 * 业务异常，用户返回给客户端的异常信息
 *
 * @author lucky.zhou
 */
public class BaseBizException extends RuntimeException {

    @Getter
    private String code;

    @Getter
    private String msg;

    public BaseBizException() {
        super();
    }

    public BaseBizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseBizException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }


    public BaseBizException(BaseErrorCode err) {
        super();
        this.code = err.getCode();
        this.msg = err.getMsg();
    }


}
