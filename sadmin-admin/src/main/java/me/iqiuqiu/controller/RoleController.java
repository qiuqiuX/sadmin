package me.iqiuqiu.controller;

import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.common.util.BaseResponse;
import me.iqiuqiu.mapper.model.dto.RoleDTO;
import me.iqiuqiu.mapper.model.entity.Role;
import me.iqiuqiu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public BaseResponse add(@RequestBody Role role) {
        roleService.add(role);

        return BaseResponse.success(null);
    }

    @GetMapping
    public BaseResponse getList(PageQO pageQO) {
        RoleDTO roleDTO = roleService.getList(pageQO);

        return BaseResponse.successListPage(roleDTO.getList(), roleDTO.getTotal());
    }

    @GetMapping("/all")
    public BaseResponse getAll() {
        RoleDTO roleDTO = roleService.getAll();

        return BaseResponse.successList(roleDTO.getList());
    }

    @GetMapping("/{id}")
    public BaseResponse getDetail(@PathVariable int id) {
        RoleDTO roleDTO = roleService.getDetail(id);

        return BaseResponse.success(roleDTO.getItem());
    }

    @DeleteMapping("/{id}")
    public BaseResponse delete(@PathVariable int id) {
        roleService.delete(id);

        return BaseResponse.success(null);
    }

    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable int id, @RequestBody Role role) {
        role.setId(id);
        roleService.update(role);

        return BaseResponse.success(null);
    }

}
