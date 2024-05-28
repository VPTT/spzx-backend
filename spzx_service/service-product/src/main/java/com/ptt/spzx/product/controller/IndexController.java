package com.ptt.spzx.product.controller;

import com.ptt.spzx.model.entity.product.Category;
import com.ptt.spzx.model.entity.product.ProductSku;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.h5.IndexVo;
import com.ptt.spzx.product.service.CategoryService;
import com.ptt.spzx.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: IndexController
 * Package: com.ptt.spzx.product.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/16 15:16
 * @Version 1.0
 */
@Tag(name = "首页接口管理")
@RestController
@RequestMapping(value="/api/product/index")
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result findData(){
        List<Category> categoryList=categoryService.findData();
        List<ProductSku> productSkuList=productService.findData();


        IndexVo indexVo = new IndexVo();
        indexVo.setProductSkuList(productSkuList);
        indexVo.setCategoryList(categoryList);
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }

}
