package me.iqiuqiu.service;

import me.iqiuqiu.mapper.model.dto.RoleDTO;
import me.iqiuqiu.mapper.model.entity.Role;
import me.iqiuqiu.common.model.qo.PageQO;

public interface RoleService {
    void add(Role role);

    RoleDTO getList(PageQO pageQO);

    void delete(int id);

    void update(Role role);

    RoleDTO getDetail(int id);

    RoleDTO getAll();


}
