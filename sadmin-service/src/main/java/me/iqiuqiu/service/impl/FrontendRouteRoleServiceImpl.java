package me.iqiuqiu.service.impl;

import me.iqiuqiu.mapper.mapper.FrontendRouteRoleMapper;
import me.iqiuqiu.mapper.model.entity.AdminRole;
import me.iqiuqiu.mapper.model.entity.FrontendRoute;
import me.iqiuqiu.mapper.model.entity.FrontendRouteRole;
import me.iqiuqiu.mapper.model.entity.Role;
import me.iqiuqiu.service.FrontendRouteRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FrontendRouteRoleServiceImpl implements FrontendRouteRoleService {

    @Autowired
    private FrontendRouteRoleMapper frontendRouteRoleMapper;

    @Override
    public void addRoleFrontendRoutes(Role role) {
        List<FrontendRouteRole> list = new ArrayList<>();
        for (FrontendRoute FrontendRoute : role.getFrontendRoutes()) {
            FrontendRouteRole frontendRouteRole = new FrontendRouteRole();
            frontendRouteRole.setRoleId(role.getId());
            frontendRouteRole.setFrontendRouteId(FrontendRoute.getId());
            list.add(frontendRouteRole);
        }
        frontendRouteRoleMapper.insertBatch(list);
    }
}
