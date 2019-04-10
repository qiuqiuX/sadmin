package me.iqiuqiu.mapper.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
public class Admin {
    @Id
    @Column(updatable = false, insertable = false)
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Column(updatable = false)
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户状态：0启用，1禁用
     */
    @Column(name = "is_disable")
    private Boolean isDisable;

    /**
     * 登录次数
     */
    @Column(name = "login_times")
    private Integer loginTimes;

    /**
     * 删除时间
     */
    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "PRC")
    private Date createdAt;

    private List<Role> roles;


}