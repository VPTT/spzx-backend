package com.ptt.spzx.product.controller;

import com.ptt.spzx.model.entity.product.Brand;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.product.mapper.BrandMapper;
import com.ptt.spzx.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: BrandController
 * Package: com.ptt.spzx.product.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 14:53
 * @Version 1.0
 */
@Tag(name = "品牌管理")
@RestController
@RequestMapping(value="/api/product/brand")
@SuppressWarnings({"unchecked", "rawtypes"})
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Operation(summary = "获取全部品牌")
    @GetMapping("findAll")
    public Result<List<Brand>> findAll() {
        List<Brand> brandList=brandService.findAll();
        return Result.build(brandList, ResultCodeEnum.SUCCESS);
    }
}
