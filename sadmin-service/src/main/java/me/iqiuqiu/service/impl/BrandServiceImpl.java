package me.iqiuqiu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.mapper.mapper.BrandMapper;
import me.iqiuqiu.mapper.model.dto.BrandDTO;
import me.iqiuqiu.mapper.model.entity.Brand;
import me.iqiuqiu.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BrandDTO getList(PageQO pageQO) {
        Page<Object> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<Brand> brands = brandMapper.selectAll();

        BrandDTO brandDto = new BrandDTO();
        brandDto.setList(brands);
        brandDto.setTotal(page.getTotal());

        return brandDto;
    }

    @Override
    public BrandDTO getDetail(int id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);

        BrandDTO brandDto = new BrandDTO();
        brandDto.setItem(brand);

        return brandDto;
    }

    @Override
    public void add(Brand brand) {
        brand.setCreatedAt(new Date());

        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(int id) {
        brandMapper.deleteByPrimaryKey(id);
    }
}
