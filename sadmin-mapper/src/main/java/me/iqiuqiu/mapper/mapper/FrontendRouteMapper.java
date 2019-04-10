package me.iqiuqiu.mapper.mapper;

import me.iqiuqiu.mapper.model.entity.FrontendRoute;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FrontendRouteMapper extends Mapper<FrontendRoute> {

    List<FrontendRoute> getFrontendRoutesByIds(List<Integer> frontendRouteIds);

    List<FrontendRoute> selectDisplayableParentOrderList();
}