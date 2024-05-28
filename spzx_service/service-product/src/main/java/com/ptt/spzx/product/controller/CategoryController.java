package com.ptt.spzx.product.controller;

import com.ptt.spzx.model.entity.product.Category;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.ptt.spzx.product.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/16 16:26
 * @Version 1.0
 */
@Tag(name = "分类接口管理")
@RestController
@RequestMapping(value="/api/product/category")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Operation(summary = "获取分类树形数据")
    @GetMapping("findCategoryTree")
    public Result findCategoryTree(){
        List<Category> categoryList=categoryService.findCategoryTree();
        return Result.build(categoryList, ResultCodeEnum.SUCCESS);
    }
}
