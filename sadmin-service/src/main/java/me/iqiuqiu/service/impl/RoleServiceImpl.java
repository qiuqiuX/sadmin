package me.iqiuqiu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import me.iqiuqiu.common.constant.StatusMessageEnum;
import me.iqiuqiu.common.exception.CustomException;
import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.mapper.mapper.FrontendRouteRoleMapper;
import me.iqiuqiu.mapper.mapper.RoleMapper;
import me.iqiuqiu.mapper.model.dto.RoleDTO;
import me.iqiuqiu.mapper.model.entity.Role;
import me.iqiuqiu.service.FrontendRouteRoleService;
import me.iqiuqiu.service.FrontendRouteService;
import me.iqiuqiu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private FrontendRouteRoleMapper frontendRouteRoleMapper;

    @Autowired
    private FrontendRouteRoleService frontendRouteRoleService;

    @Autowired
    private FrontendRouteService frontendRouteService;

    @Override
    public void add(Role role) {
        Role selectByName = roleMapper.selectByName(role.getName());
        if (selectByName != null) {
            throw new CustomException(StatusMessageEnum.CONFLICT);
        }
        role.setCreatedAt(new Date());
        role.setDescription("");
        roleMapper.insert(role);
        frontendRouteRoleService.addRoleFrontendRoutes(role);
    }

    @Override
    public RoleDTO getList(PageQO pageQO) {
        Page<Object> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<Role> roles = roleMapper.selectAll();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setList(roles);
        roleDTO.setTotal(page.getTotal());

        return roleDTO;
    }

    @Override
    public void delete(int id) {
        roleMapper.deleteByPrimaryKey(id);
        // TODO 删除关联角色权限等
        frontendRouteRoleMapper.deleteByRoleId(id);
    }

    @Override
    public void update(Role role) {
        Role role1 = roleMapper.selectRoleByNameAndNotIncludeSelf(role);
        if (role1 != null) {
            throw new CustomException(StatusMessageEnum.CONFLICT);
        }
        roleMapper.updateByPrimaryKeySelective(role);
        frontendRouteRoleMapper.deleteByRoleId(role.getId());
        frontendRouteRoleService.addRoleFrontendRoutes(role);
    }

    @Override
    public RoleDTO getDetail(int id) {
        Role role = roleMapper.selectByPrimaryKey(id);

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setItem(role);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(id);
        role.setFrontendRoutes(frontendRouteService.getFrontendRoutesByRoleIds(list));

        return roleDTO;
    }

    @Override
    public RoleDTO getAll() {
        List<Role> roles = roleMapper.selectAll();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setList(roles);

        return roleDTO;
    }
}
