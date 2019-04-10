package me.iqiuqiu.controller;

import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.common.util.BaseResponse;
import me.iqiuqiu.mapper.model.dto.AdminDTO;
import me.iqiuqiu.mapper.model.entity.Admin;
import me.iqiuqiu.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

//    @RequestMapping("/info")
//    public BaseResponse info() {
//        System.out.println("infos");
//        Subject subject = SecurityUtils.getSubject();
//        Admin principal = (Admin) subject.getPrincipal();
//
//        return principal;
//    }

    @GetMapping
    public BaseResponse getList(PageQO pageQO) {
        AdminDTO adminDTO = adminService.getList(pageQO);
        return BaseResponse.successListPage(adminDTO.getList(), adminDTO.getTotal());
    }

    @GetMapping("/{id}")
    public BaseResponse getDetail(@PathVariable int id) {
        AdminDTO adminDTO = adminService.getAdminByUserId(id);
        return BaseResponse.success(adminDTO.getItem());
    }

    @PostMapping
    public BaseResponse add(@RequestBody Admin admin) {
        adminService.add(admin);
//        System.out.println(admin);
        return BaseResponse.success();
    }

    @DeleteMapping("/{id}")
    public BaseResponse delete(@PathVariable int id) {
        adminService.delete(id);
        return BaseResponse.success();
    }

    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable int id, @RequestBody Admin admin) {
        admin.setId(id);
        admin.setPassword(null);
        adminService.update(admin);

        return BaseResponse.success();
    }

    @PutMapping("/{id}/password")
    public BaseResponse updatePassword(@PathVariable int id, @RequestBody Admin admin) {
        String password = admin.getPassword();
        adminService.updatePasswordById(id, password);

        return BaseResponse.success();
    }

    @PutMapping("/{id}/status")
    public BaseResponse updateStatus(@PathVariable int id, @RequestBody Admin admin) {
        admin.setId(id);
        adminService.update(admin);

        return BaseResponse.success();
    }

}
