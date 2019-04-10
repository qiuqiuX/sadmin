package me.iqiuqiu.mapper.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "frontend_route")
@Data
public class FrontendRoute {
    @Id
    @Column(updatable = false, insertable = false)
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 路由
     */
    private String route;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否显示：0否，1是
     */
    @Column(name = "is_show")
    private Boolean isShow;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "PRC")
    private Date createdAt;

    private List<FrontendRoute> frontendRoutes = new ArrayList<>();

}