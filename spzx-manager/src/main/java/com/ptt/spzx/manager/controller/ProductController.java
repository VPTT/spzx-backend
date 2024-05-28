package com.ptt.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.manager.service.ProductService;
import com.ptt.spzx.model.dto.product.ProductDto;
import com.ptt.spzx.model.entity.product.Product;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ProductController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/12 10:54
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable Integer page, @PathVariable Integer limit, ProductDto productDto){
        PageInfo<Product> productPageInfo=productService.findByPage(page,limit,productDto);
        return Result.build(productPageInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable  Integer id){
        Product product=productService.getById(id);
        return Result.build(product,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/updateById")
    public  Result updateById(@RequestBody Product product){
        productService.updateById(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id){
        productService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @PutMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }




}
