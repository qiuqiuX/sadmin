package me.iqiuqiu.service;

import me.iqiuqiu.mapper.model.entity.Admin;

public interface AdminRoleService {

    void addAdminRoles(Admin admin);

    void deleteRolesByAdminId(int adminId);

    void syncRolesByAdmin(Admin admin);
}
