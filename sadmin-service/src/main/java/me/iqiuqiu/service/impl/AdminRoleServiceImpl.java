package me.iqiuqiu.service.impl;

import me.iqiuqiu.mapper.mapper.AdminRoleMapper;
import me.iqiuqiu.mapper.model.entity.Admin;
import me.iqiuqiu.mapper.model.entity.AdminRole;
import me.iqiuqiu.mapper.model.entity.Role;
import me.iqiuqiu.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public void addAdminRoles(Admin admin) {
        List<AdminRole> list = new ArrayList<>();
        for (Role role : admin.getRoles()) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin.getId());
            adminRole.setRoleId(role.getId());
            list.add(adminRole);
        }
        adminRoleMapper.insertBatch(list);
    }

    @Override
    public void deleteRolesByAdminId(int adminId) {
        adminRoleMapper.deleteRolesByAdminId(adminId);
    }

    public void syncRolesByAdmin(Admin admin) {
        deleteRolesByAdminId(admin.getId());
        addAdminRoles(admin);
    }

}
