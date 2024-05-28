package com.ptt.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.common.log.annotation.Log;
import com.ptt.spzx.common.log.enums.OperatorType;
import com.ptt.spzx.manager.service.BrandService;
import com.ptt.spzx.model.entity.product.Brand;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: brandController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 14:30
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Log(title = "品牌列表",businessType = 0,operatorType = OperatorType.OTHER)
    @GetMapping("{page}/{size}")
    public Result findByPages(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        PageInfo<Brand> brandPageInfo=brandService.findByPages(page,size);
        return Result.build(brandPageInfo, ResultCodeEnum.SUCCESS);
    }
    @PostMapping("save")
    public Result save(@RequestBody Brand brand){
        brandService.save(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @PutMapping("updateById")
    public Result updateById(@RequestBody Brand brand)
    {
        brandService.updateById(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);

    }
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Integer id){
        brandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);

    }
    @GetMapping("/findAll")
    public Result findAll(){
        List<Brand> brands=brandService.findAll();
        return Result.build(brands,ResultCodeEnum.SUCCESS);
    }

}
