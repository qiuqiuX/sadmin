package me.iqiuqiu.mapper.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "frontend_route_role")
@Data
public class FrontendRouteRole {
    @Id
    @Column(name = "frontend_route_id")
    private Integer frontendRouteId;

    @Id
    @Column(name = "role_id")
    private Integer roleId;

}