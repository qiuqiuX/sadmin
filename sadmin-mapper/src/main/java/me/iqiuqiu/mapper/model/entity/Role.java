package me.iqiuqiu.mapper.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
public class Role {
    @Id
    @Column(updatable = false, insertable = false)
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "PRC")
    private Date createdAt;

    private List<FrontendRoute> frontendRoutes;

}