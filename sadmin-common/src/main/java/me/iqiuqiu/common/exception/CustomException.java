package me.iqiuqiu.common.exception;

import lombok.Getter;
import me.iqiuqiu.common.constant.StatusMessageEnum;

@Getter
public class CustomException extends RuntimeException {

    private StatusMessageEnum statusMessage;
    private String errorMsg;

    public CustomException() {
        this(StatusMessageEnum.INTERNAL_SERVER_ERROR);
    }

    public CustomException(StatusMessageEnum statusMessage) {
        this(statusMessage, statusMessage.getMessage());
    }

    public CustomException(String message) {
        this(StatusMessageEnum.INTERNAL_SERVER_ERROR, message);
    }

    public CustomException (StatusMessageEnum statusMessage, String message) {
        this.errorMsg = message;
        this.statusMessage = statusMessage;
    }
}
