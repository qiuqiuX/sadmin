package me.iqiuqiu.controller;

import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.common.util.BaseResponse;
import me.iqiuqiu.mapper.model.dto.BrandDTO;
import me.iqiuqiu.mapper.model.entity.Brand;
import me.iqiuqiu.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public BaseResponse getList(PageQO pageQO) {
        BrandDTO brandDto = brandService.getList(pageQO);

        return BaseResponse.successListPage(brandDto.getList(), brandDto.getTotal());
    }

    @GetMapping("/{id}")
    public BaseResponse getDetail(@PathVariable int id) {
        BrandDTO brandDto = brandService.getDetail(id);

        return BaseResponse.success(brandDto.getItem());
    }

    @PostMapping
    public BaseResponse add(@RequestBody Brand brand) {
        brandService.add(brand);

        return BaseResponse.success();
    }

    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable int id, @RequestBody Brand brand) {
        brand.setId(id);
        brandService.update(brand);

        return BaseResponse.success();
    }

    @DeleteMapping("/{id}")
    public BaseResponse delete(@PathVariable int id) {
        brandService.delete(id);

        return BaseResponse.success();
    }

}
