package me.iqiuqiu.mapper.mapper;

import me.iqiuqiu.mapper.model.entity.AdminRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AdminRoleMapper extends Mapper<AdminRole> {

    void insertBatch(List<AdminRole> adminRoles);

    void deleteRolesByAdminId(int adminId);

    List<Integer> selectRoleIdsByAdminId(int adminId);
}