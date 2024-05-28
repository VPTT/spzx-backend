package com.ptt.spzx.manager.controller;

import com.ptt.spzx.manager.service.CategoryService;
import com.ptt.spzx.model.entity.product.Category;
import com.ptt.spzx.model.entity.system.SysMenu;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/10 15:37
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //懒加载 每次只查询一层
    @Operation(summary = "根据parentId获取下级节点")
    @GetMapping("/findByParentId/{parentId}")
    public Result findByParentId(@PathVariable Long parentId){
        List<Category> list=categoryService.findByParentId(parentId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
    @GetMapping(value = "/exportData")
    public Result exportData(HttpServletResponse response){
        categoryService.exportData(response);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        categoryService.importData(file);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }






}
