package com.marsh.zutils.web;

import com.marsh.zutils.entity.BaseResponse;
import com.marsh.zutils.exception.BaseBizException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExpHandler {

    @ExceptionHandler(BaseBizException.class)
    public BaseResponse<Void> baseBizExceptionHandler(BaseBizException ex) {
        return BaseResponse.fail(ex.getCode(), ex.getMsg());
    }


//    @ExceptionHandler(FileUploadBase.SizeLimitExceededException.class)
//    public BaseResponse<Void> sizeLimitExceededExceptionHandler(FileUploadBase.SizeLimitExceededException ex) {
//        return BaseResponse.fail("当前文件尺寸过大，超过10M");
//    }
}
