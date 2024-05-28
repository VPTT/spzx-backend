package com.ptt.spzx.manager.controller;

import com.ptt.spzx.manager.service.ProductUnitService;
import com.ptt.spzx.model.entity.base.ProductUnit;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {

    @Autowired
    private ProductUnitService productUnitService;
    
    @GetMapping("findAll")
    public Result<List<ProductUnit>> findAll() {
        List<ProductUnit> productUnitList = productUnitService.findAll();
        return Result.build(productUnitList , ResultCodeEnum.SUCCESS) ;
    }
}