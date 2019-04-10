package me.iqiuqiu.service;

import me.iqiuqiu.mapper.model.dto.AdminDTO;
import me.iqiuqiu.mapper.model.entity.Admin;

public interface LoginService {

    AdminDTO login(Admin admin);

}
