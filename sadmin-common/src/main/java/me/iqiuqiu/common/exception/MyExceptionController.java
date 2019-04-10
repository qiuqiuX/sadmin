package me.iqiuqiu.common.exception;

import lombok.extern.slf4j.Slf4j;
import me.iqiuqiu.common.constant.StatusMessageEnum;
import me.iqiuqiu.common.util.BaseResponse;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class MyExceptionController implements ErrorController {

    @RequestMapping("/error")
    public BaseResponse error(HttpServletRequest request) {
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return BaseResponse.fail(StatusMessageEnum.NOT_FOUND);
        }
        if (exception == null) {
            return BaseResponse.fail(StatusMessageEnum.INTERNAL_SERVER_ERROR);
        }

        Throwable causeException;
        if (exception.getCause() != null) {
            causeException = exception.getCause();
        } else {
            causeException = exception;
        }
        causeException.printStackTrace();
        if (causeException instanceof CustomException) {
            CustomException customException = (CustomException) causeException;
            return BaseResponse.fail(customException.getStatusMessage(),
                    customException.getErrorMsg() == null ? customException.getStatusMessage().getMessage() : customException.getErrorMsg());
        } else if (causeException instanceof UnknownAccountException || causeException instanceof UnauthenticatedException || causeException instanceof IncorrectCredentialsException) {
            return BaseResponse.fail(StatusMessageEnum.UNAUTHORIZED);
        }
        return BaseResponse.fail(StatusMessageEnum.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
