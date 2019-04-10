package me.iqiuqiu.mapper.model.dto;

import lombok.Data;
import me.iqiuqiu.mapper.model.entity.Admin;

import java.util.List;

@Data
public class AdminDTO extends BaseDTO<Admin> {

    private String token;

    public void setAdmin(Admin admin) {
        this.item = admin;
        this.item.setPassword(null);
    }

    public void setList(List<Admin> admins) {
        this.list = admins;
        for (Admin item : this.list) {
            item.setPassword(null);
        }
    }

}
