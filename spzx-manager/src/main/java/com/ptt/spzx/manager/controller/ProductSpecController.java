package com.ptt.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.manager.service.ProductSpecService;
import com.ptt.spzx.model.entity.product.ProductSpec;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: ProductSpecController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 17:09
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/product/productSpec")
public class ProductSpecController {
    @Autowired
    private ProductSpecService productSpecService;

    @GetMapping("{page}/{limit}")
    public Result findByPage(@PathVariable Integer page,@PathVariable Integer limit)
    {
        PageInfo<ProductSpec> specList=productSpecService.findByPage(page,limit);
        return Result.build(specList, ResultCodeEnum.SUCCESS);

    }

    @Operation(summary = "规格添加接口")
    @PostMapping("save")
    public Result save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "规格修改接口")
    @PutMapping("updateById")
    public Result updateById(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "商品规格删除接口")
    @DeleteMapping("/deleteById/{id}")
    public Result removeById(@PathVariable Long id) {
        productSpecService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "商品规格查询")
    @GetMapping("findAll")
    public Result findAll() {
        List<ProductSpec> list = productSpecService.findAll();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }


}
