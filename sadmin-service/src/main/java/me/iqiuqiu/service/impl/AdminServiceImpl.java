package me.iqiuqiu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import me.iqiuqiu.common.constant.StatusMessageEnum;
import me.iqiuqiu.common.exception.CustomException;
import me.iqiuqiu.mapper.mapper.AdminRoleMapper;
import me.iqiuqiu.mapper.mapper.RoleMapper;
import me.iqiuqiu.mapper.model.dto.AdminDTO;
import me.iqiuqiu.mapper.model.entity.Admin;
import me.iqiuqiu.mapper.model.entity.AdminRole;
import me.iqiuqiu.mapper.model.entity.Role;
import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.mapper.mapper.AdminMapper;
import me.iqiuqiu.mapper.mapper.AdminRoleMapper;
import me.iqiuqiu.service.AdminRoleService;
import me.iqiuqiu.service.AdminService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private RoleMapper roleMapper;


//    @Override
//    public List<String> getPermissionsByAdminId(Integer id) {
//        return adminMapper.getPermissionsByAdminId(id);
//    }

    @Override
    public AdminDTO getAdminByUserId(int id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        List<Integer> roleIds = adminRoleMapper.selectRoleIdsByAdminId(id);
        if (roleIds != null && roleIds.size() > 0) {
            admin.setRoles(roleMapper.selectByIds(roleIds));
        }
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdmin(admin);

        return adminDTO;
    }

    @Override
    public AdminDTO getList(PageQO pageQO) {
        Page<Object> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<Admin> admins = adminMapper.selectAll();

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setList(admins);
        adminDTO.setTotal(page.getTotal());
        return adminDTO;
    }


    @Override
    public void delete(int id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Admin admin) {
        Admin adminByUsername = adminMapper.getAdminByUsername(admin.getUsername());
        if (adminByUsername != null) {
            throw new CustomException(StatusMessageEnum.CONFLICT, "该账号已存在~");
        }
        // 密码加密
        admin.setPassword(getPasswordHash(admin.getPassword(), admin.getUsername()));
        // 默认属性
        admin.setAvatar("");
        admin.setCreatedAt(new Date());
        admin.setLoginTimes(0);

        adminMapper.insert(admin);
        System.out.println(admin);
        // TODO 设置角色
        adminRoleService.addAdminRoles(admin);
    }

    @Override
    public void updatePasswordById(int id, String password) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        String newPassword = getPasswordHash(password, admin.getUsername());
        Admin updateAdmin = new Admin();
        updateAdmin.setId(id);
        updateAdmin.setPassword(newPassword);
        update(updateAdmin);
//        adminMapper.updateByPrimaryKey(updateAdmin);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
        // 更新角色
        if (admin.getRoles() != null) {
            adminRoleService.syncRolesByAdmin(admin);
        }
    }

    @Override
    public AdminDTO getAdminByUsernameAndPassword(String username, String password) {
        Admin admin = adminMapper.getUser(username, password);
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdmin(admin);

        return adminDTO;
    }

    @Override
    public String getPasswordHash(String password, String username) {
        Md5Hash md5Hash = new Md5Hash(password, username, 2);

        return md5Hash.toString();
    }
}
