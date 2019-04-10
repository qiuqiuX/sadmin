package me.iqiuqiu.service;

import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.mapper.model.dto.BrandDTO;
import me.iqiuqiu.mapper.model.entity.Brand;

public interface BrandService {

    BrandDTO getList(PageQO pageQO);

    BrandDTO getDetail(int id);

    void add(Brand brand);

    void update(Brand brand);

    void delete(int id);

}
