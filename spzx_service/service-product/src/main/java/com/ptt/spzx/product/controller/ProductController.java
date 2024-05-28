package com.ptt.spzx.product.controller;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.dto.h5.ProductSkuDto;
import com.ptt.spzx.model.dto.product.SkuSaleDto;
import com.ptt.spzx.model.entity.product.Product;
import com.ptt.spzx.model.entity.product.ProductSku;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.h5.ProductItemVo;
import com.ptt.spzx.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: ProductController
 * Package: com.ptt.spzx.product.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 15:24
 * @Version 1.0
 */
@Tag(name = "商品列表管理")
@RestController
@RequestMapping(value="/api/product")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "分页查询")
    @GetMapping(value = "/{page}/{limit}")
    public Result findByPage(@PathVariable Integer page,
                             @PathVariable Integer limit,
                             ProductSkuDto productSkuDto){
        PageInfo<ProductSku> productPageInfo=productService.findByPage(page,limit,productSkuDto);
        return Result.build(productPageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "商品详情")
    @GetMapping("item/{skuId}")
    public Result getItem(@PathVariable Long skuId){
        ProductItemVo productItemVo= productService.getItem(skuId);
        return Result.build(productItemVo,ResultCodeEnum.SUCCESS);
    }

    //远程调用接口 根据skuid返回sku信息
    @GetMapping("/getBySkuId/{skuId}")
    public ProductSku getBySkuId(@PathVariable Long skuId){
        ProductSku productSku= productService.getBySkuId(skuId);
        return productSku;
    }

    @Operation(summary = "更新商品sku销量")
    @PostMapping("updateSkuSaleNum")
    public Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList) {
        return productService.updateSkuSaleNum(skuSaleDtoList);
    }


}
