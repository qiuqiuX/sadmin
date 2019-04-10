package me.iqiuqiu.mapper.mapper;

import me.iqiuqiu.mapper.model.entity.FrontendRouteRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FrontendRouteRoleMapper extends Mapper<FrontendRouteRole> {

    void insertBatch(List<FrontendRouteRole> frontendRouteRoles);

    List<Integer> getFrontendRouteIdsByRoleIds(List<Integer> roleIds);

    void deleteByRoleId(int roleId);

    void deleteByFrontendRouteId(int frontendRouteId);
}