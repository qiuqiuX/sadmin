package me.iqiuqiu.common.exception;

import lombok.extern.slf4j.Slf4j;
import me.iqiuqiu.common.constant.StatusMessageEnum;
import me.iqiuqiu.common.util.BaseResponse;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class MyControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception e) {
        return BaseResponse.fail(StatusMessageEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public BaseResponse handleUnauthenticatedException(UnauthenticatedException e) {
        return BaseResponse.fail(StatusMessageEnum.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomException.class)
    public BaseResponse handleCustomException(CustomException e) {
        return BaseResponse.fail(e.getStatusMessage(), e.getErrorMsg() == null ? e.getStatusMessage().getMessage() : e.getErrorMsg());
    }

}
