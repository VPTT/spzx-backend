package com.ptt.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.manager.service.CategoryBrandService;
import com.ptt.spzx.model.dto.product.CategoryBrandDto;
import com.ptt.spzx.model.entity.product.Brand;
import com.ptt.spzx.model.entity.product.Category;
import com.ptt.spzx.model.entity.product.CategoryBrand;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CategoryBrandController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 15:30
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
public class CategoryBrandController {
    @Autowired
    private CategoryBrandService categoryBrandService;

    @GetMapping("/{page}/{limit}")
    public Result findBypage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                             CategoryBrandDto categoryBrandDto){
        PageInfo<CategoryBrand> categoryBrandPageInfo =categoryBrandService.findByPage(page,limit,categoryBrandDto);
        return Result.build(categoryBrandPageInfo, ResultCodeEnum.SUCCESS);

    }

    @PostMapping("save")
    public Result save(@RequestBody CategoryBrand categoryBrand)
    {
        categoryBrandService.save(categoryBrand);
        return Result.build((null), ResultCodeEnum.SUCCESS);

    }

    @PutMapping("updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand){
        categoryBrandService.updateById(categoryBrand);
        return Result.build((null), ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id)
    {
        categoryBrandService.deleteById(id);
        return Result.build((null), ResultCodeEnum.SUCCESS);

    }
    @GetMapping("findBrandByCategoryId/{CategoryId}")
    public Result findBrandByCategoryId(@PathVariable Long CategoryId){
          List<Brand> brandList=categoryBrandService.findBrandByCategoryId(CategoryId);
          return Result.build(brandList,ResultCodeEnum.SUCCESS);
    }




}
