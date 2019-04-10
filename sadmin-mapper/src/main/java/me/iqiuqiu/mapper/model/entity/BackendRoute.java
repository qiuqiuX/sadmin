package me.iqiuqiu.mapper.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "backend_route")
public class BackendRoute {
    @Id
    private Integer id;

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

    @Column(name = "created_at")
    private Date createdAt;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取路由
     *
     * @return route - 路由
     */
    public String getRoute() {
        return route;
    }

    /**
     * 设置路由
     *
     * @param route 路由
     */
    public void setRoute(String route) {
        this.route = route;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return created_at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}