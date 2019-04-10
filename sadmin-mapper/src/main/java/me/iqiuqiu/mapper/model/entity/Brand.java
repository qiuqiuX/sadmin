package me.iqiuqiu.mapper.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import me.iqiuqiu.common.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

@Data
public class Brand {

    @Autowired
    HttpServletRequest request;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(updatable = false, insertable = false)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * LOGO
     */
    private String logo;

    @Transient
    private String logoUrl;

    /**
     * 是否禁用：0否，1是
     */
    @Column(name = "is_disable")
    private Boolean isDisable;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "PRC")
    private Date createdAt;

    public String getLogoUrl() {
        return FileUtil.getFileUrl(logo);
    }

}