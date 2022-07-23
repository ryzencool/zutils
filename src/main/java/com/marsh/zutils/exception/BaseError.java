package com.marsh.zutils.exception;

/**
 * 业务异常码，用于返回给前端
 *
 * @author lucky.zhou
 */
public class BaseError {

    public static final BaseErrorCode SUCCESS = new BaseErrorCode("000000", "SUCCESS");

    public static final BaseErrorCode FAIL = new BaseErrorCode("000001", "FAIL");

    public static final BaseErrorCode LACK_ACCESS_TOKEN = new BaseErrorCode("100001", "access_token不能为空");

    public static final BaseErrorCode EXPIRED_ACCESS_TOKEN = new BaseErrorCode("100002", "access_token已过期");




}
