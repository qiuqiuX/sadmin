package me.iqiuqiu.mapper.mapper;

import me.iqiuqiu.mapper.model.entity.BackendRoute;
import me.iqiuqiu.mapper.model.entity.FrontendRoute;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BackendRouteMapper extends Mapper<BackendRoute> {

    List<FrontendRoute> selectFrontendRouteByRoleIds(List<Integer> roleIds);

}