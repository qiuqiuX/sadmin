package me.iqiuqiu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import me.iqiuqiu.common.constant.StatusMessageEnum;
import me.iqiuqiu.common.exception.CustomException;
import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.mapper.mapper.AdminRoleMapper;
import me.iqiuqiu.mapper.mapper.FrontendRouteMapper;
import me.iqiuqiu.mapper.mapper.FrontendRouteRoleMapper;
import me.iqiuqiu.mapper.mapper.RoleMapper;
import me.iqiuqiu.mapper.model.dto.FrontendRouteDTO;
import me.iqiuqiu.mapper.model.entity.Admin;
import me.iqiuqiu.mapper.model.entity.FrontendRoute;
import me.iqiuqiu.service.FrontendRouteService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class FrontendRouteServiceImpl implements FrontendRouteService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private FrontendRouteRoleMapper frontendRouteRoleMapper;

    @Autowired
    private FrontendRouteMapper frontendRouteMapper;

    @Override
    public List<Map<String, Object>> getAdminMenu() {
        Admin admin = (Admin) SecurityUtils.getSubject();
        List<Integer> roleIds = adminRoleMapper.selectRoleIdsByAdminId(admin.getId());
        List<FrontendRoute> frontendRoutes = getFrontendRoutesByRoleIds(roleIds);



        return null;
    }

    @Override
    public List<FrontendRoute> getFrontendRoutesByRoleIds(List<Integer> roleIds) {
        List<Integer> frontendRouteIds = frontendRouteRoleMapper.getFrontendRouteIdsByRoleIds(roleIds);
        List<FrontendRoute> frontendRoutes = frontendRouteMapper.getFrontendRoutesByIds(frontendRouteIds);

        return frontendRoutes;
    }

    @Override
    public FrontendRouteDTO getList(PageQO pageQO) {
        Page<Object> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<FrontendRoute> frontendRoutes = frontendRouteMapper.selectAll();

        FrontendRouteDTO frontendRouteDTO = new FrontendRouteDTO();
        frontendRouteDTO.setList(frontendRoutes);
        frontendRouteDTO.setTotal(page.getTotal());

        return frontendRouteDTO;
    }

    @Override
    public FrontendRouteDTO getDetail(int id) {
        FrontendRoute frontendRoute = frontendRouteMapper.selectByPrimaryKey(id);

        FrontendRouteDTO frontendRouteDTO = new FrontendRouteDTO();
        frontendRouteDTO.setItem(frontendRoute);

        return frontendRouteDTO;
    }

    @Override
    public void add(FrontendRoute frontendRoute) {
        frontendRoute.setCreatedAt(new Date());
        frontendRoute.setDescription("");
        frontendRouteMapper.insert(frontendRoute);
    }

    @Override
    public void update(FrontendRoute frontendRoute) {
        if (frontendRoute.getId().equals(frontendRoute.getParentId())) {
            throw new CustomException(StatusMessageEnum.BAD_REQUEST, "不能选择自己作为父级菜单");
        }
        frontendRouteMapper.updateByPrimaryKeySelective(frontendRoute);
    }

    @Override
    public void delete(int id) {
        frontendRouteMapper.deleteByPrimaryKey(id);

        // TODO 删除关联后端权限
        frontendRouteRoleMapper.deleteByFrontendRouteId(id);
    }

    @Override
    public FrontendRouteDTO getTree() {
        List<FrontendRoute> frontendRoutes = frontendRouteMapper.selectDisplayableParentOrderList();

        List<FrontendRoute> tree = buildTree(frontendRoutes);

        FrontendRouteDTO frontendRouteDTO = new FrontendRouteDTO();
        frontendRouteDTO.setList(tree);

        return frontendRouteDTO;
    }

    protected List<FrontendRoute> buildTree(List<FrontendRoute> frontendRoutes) {
        List<FrontendRoute> resultFrontendRoutes = new ArrayList<>();
        Map<Integer, FrontendRoute> routeHashMap = new HashMap<>();

        for (FrontendRoute frontendRoute : frontendRoutes) {
            if (frontendRoute.getParentId() == 0) {
                resultFrontendRoutes.add(frontendRoute);
                routeHashMap.put(frontendRoute.getId(), frontendRoute);
            } else {
                FrontendRoute parentFrontendRoute = routeHashMap.get(frontendRoute.getParentId());
                parentFrontendRoute.getFrontendRoutes().add(frontendRoute);
                routeHashMap.put(frontendRoute.getId(), frontendRoute);
            }
        }

        return resultFrontendRoutes;
    }
}
