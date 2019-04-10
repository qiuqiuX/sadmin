package me.iqiuqiu.controller;

import me.iqiuqiu.common.util.BaseResponse;
import me.iqiuqiu.config.ServerCustomConfig;
import me.iqiuqiu.mapper.model.dto.AdminDTO;
import me.iqiuqiu.mapper.model.entity.Admin;
import me.iqiuqiu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ServerCustomConfig config;

    @RequestMapping("/login")
    // 前端发送的请求 Content-Type: application/json ，不是普通的 x-www-form-urlencoded，
    // 因此需要引入 json 包来解析 json，并且添加 @RequestBody 注解来获取 json 参数
    public ResponseEntity login(@Validated @RequestBody Admin admin) {
        AdminDTO adminDTO = loginService.login(admin);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", adminDTO.getToken());
        map.put("admin", adminDTO.getItem());

        return BaseResponse.success(map);
    }

    @RequestMapping("/logout")
    public ResponseEntity logout() {
        return BaseResponse.success(null);
    }

}
