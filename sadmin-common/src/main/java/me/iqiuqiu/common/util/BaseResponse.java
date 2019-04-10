package me.iqiuqiu.common.util;

import me.iqiuqiu.common.constant.StatusMessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;


public class BaseResponse extends ResponseEntity<HashMap> {

    private BaseResponse(HashMap body) {
        super(body, HttpStatus.OK);
    }

    private static BaseResponse response(Object object, int status, String errorMsg) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", object);
        hashMap.put("status", status);
        hashMap.put("msg", errorMsg);
        return new BaseResponse(hashMap);
    }

    public static BaseResponse success() {
        return response(null, StatusMessageEnum.OK.getStatus(), StatusMessageEnum.OK.getMessage());
    }

    public static BaseResponse success(Object body) {
        return response(body, StatusMessageEnum.OK.getStatus(), StatusMessageEnum.OK.getMessage());
    }

    public static BaseResponse successList(List list) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("list", list);
        return response(hashMap, StatusMessageEnum.OK.getStatus(), StatusMessageEnum.OK.getMessage());
    }

    public static BaseResponse successListPage(List list, long counnt) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("list", list);
        hashMap.put("total", counnt);
        return response(hashMap, StatusMessageEnum.OK.getStatus(), StatusMessageEnum.OK.getMessage());
    }

    public static BaseResponse fail(StatusMessageEnum statusMessage, String errorMsg) {
        return response(null, statusMessage.getStatus(), errorMsg);
    }

    public static BaseResponse fail(StatusMessageEnum statusMessage) {
        return response(null, statusMessage.getStatus(), statusMessage.getMessage());
    }

}