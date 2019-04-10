package me.iqiuqiu.mapper.mapper;

import me.iqiuqiu.mapper.model.entity.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {

    Role selectByName(String name);

    Role selectRoleByNameAndNotIncludeSelf(Role role);

    List<Role> selectByIds(List<Integer> roleIds);
}