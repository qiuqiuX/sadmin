package me.iqiuqiu.service;


import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.mapper.model.dto.FrontendRouteDTO;
import me.iqiuqiu.mapper.model.entity.FrontendRoute;

import java.util.List;
import java.util.Map;

public interface FrontendRouteService {

    List<Map<String, Object>> getAdminMenu();

    List<FrontendRoute> getFrontendRoutesByRoleIds(List<Integer> roleIds);

    FrontendRouteDTO getList(PageQO pageQO);

    FrontendRouteDTO getDetail(int id);

    void add(FrontendRoute frontendRoute);

    void update(FrontendRoute frontendRoute);

    void delete(int id);

    FrontendRouteDTO getTree();

}
