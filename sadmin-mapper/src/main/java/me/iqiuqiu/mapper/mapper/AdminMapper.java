package me.iqiuqiu.mapper.mapper;

import me.iqiuqiu.mapper.model.entity.Admin;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AdminMapper extends Mapper<Admin> {

    Admin getAdminByUsername(String username);

    Admin getUser(String username, String password);

}