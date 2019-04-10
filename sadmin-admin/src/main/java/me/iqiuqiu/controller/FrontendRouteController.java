package me.iqiuqiu.controller;

import me.iqiuqiu.common.model.qo.PageQO;
import me.iqiuqiu.common.util.BaseResponse;
import me.iqiuqiu.mapper.model.dto.FrontendRouteDTO;
import me.iqiuqiu.mapper.model.entity.FrontendRoute;
import me.iqiuqiu.service.FrontendRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("frontend_routes")
public class FrontendRouteController {

    @Autowired
    private FrontendRouteService frontendRouteService;

    public BaseResponse getAdminMenu() {


        return BaseResponse.success("");
    }

    @GetMapping("/tree")
    public BaseResponse getTree() {
        FrontendRouteDTO frontendRouteDTO = frontendRouteService.getTree();

        return BaseResponse.successList(frontendRouteDTO.getList());
    }

    @GetMapping
    public BaseResponse getList(PageQO pageQO) {
        FrontendRouteDTO frontendRouteDTO = frontendRouteService.getList(pageQO);

        return BaseResponse.successListPage(frontendRouteDTO.getList(), frontendRouteDTO.getTotal());
    }

    @GetMapping("/{id}")
    public BaseResponse getDetail(@PathVariable int id) {
        FrontendRouteDTO frontendRouteDTO = frontendRouteService.getDetail(id);

        return BaseResponse.success(frontendRouteDTO.getItem());
    }

    @PostMapping
    public BaseResponse add(@RequestBody FrontendRoute frontendRoute) {
        frontendRouteService.add(frontendRoute);

        return BaseResponse.success();
    }

    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable int id, @RequestBody FrontendRoute frontendRoute) {
        frontendRoute.setId(id);
        frontendRouteService.update(frontendRoute);

        return BaseResponse.success();
    }

    @DeleteMapping("/{id}")
    public BaseResponse delete(@PathVariable int id) {
        frontendRouteService.delete(id);

        return BaseResponse.success();
    }

}
