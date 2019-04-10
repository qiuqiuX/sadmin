package me.iqiuqiu.service.impl;

import me.iqiuqiu.common.constant.StatusMessageEnum;
import me.iqiuqiu.common.exception.CustomException;
import me.iqiuqiu.mapper.mapper.AdminMapper;
import me.iqiuqiu.mapper.model.dto.AdminDTO;
import me.iqiuqiu.mapper.model.entity.Admin;
import me.iqiuqiu.service.AdminService;
import me.iqiuqiu.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminDTO login(Admin admin) {
        Admin loginAdmin = adminMapper.getAdminByUsername(admin.getUsername());
        if (loginAdmin == null) {
            throw new CustomException(StatusMessageEnum.UNKNOWN_ACCOUNT);
        }
        if (!loginAdmin.getPassword().equals(adminService.getPasswordHash(admin.getPassword(), loginAdmin.getUsername()))) {
            throw new CustomException(StatusMessageEnum.INCORRECT_PASSWORD);
        }

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), "", admin.getUsername());
        currentUser.login(token);

         String sessionId = SecurityUtils.getSubject().getSession().getId().toString();

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdmin(loginAdmin);
        adminDTO.setToken(sessionId);

        return adminDTO;
    }

}
