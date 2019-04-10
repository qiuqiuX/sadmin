package me.iqiuqiu.service;

import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.mapper.model.entity.Admin;
import me.iqiuqiu.mapper.model.dto.AdminDTO;
//import me.iqiuqiu.entity.PageListRes;
//import me.iqiuqiu.entity.QueryVo;


public interface AdminService {

//    List<String> getPermissionsByAdminId(Integer id);

    AdminDTO getAdminByUserId(int id);

    AdminDTO getList(PageQO pageQO);


    void delete(int id);

    void add(Admin admin);

    void updatePasswordById(int id, String password);

    void update(Admin admin);

    AdminDTO getAdminByUsernameAndPassword(String username, String password);

    String getPasswordHash(String password, String username);
}
