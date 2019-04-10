package me.iqiuqiu.common.constant;

public enum StatusMessageEnum {
    OK(1, "ok"),
    BAD_REQUEST(400, "请求参数错误,请确认~"),
    UNAUTHORIZED(401, "用户认证失败,请重现登录~"),
    NOT_FOUND(404, "您查找的页面不存在,请确认~"),
    CONFLICT(409, "数据已存在,请确认~"),
    INTERNAL_SERVER_ERROR(500, "系统繁忙,请稍后再试~"),
    DATA_NOT_FOUND(999, "您查找的数据不存在,请确认~"),
    UNKNOWN_ACCOUNT(1000, "账号不存在,请重试~"),
    INCORRECT_PASSWORD(1001, "密码错误,请重试~"),
    ;

    private Integer status;
    private String message;

    StatusMessageEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
