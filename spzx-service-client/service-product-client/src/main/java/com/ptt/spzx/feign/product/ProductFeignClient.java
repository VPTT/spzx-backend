package com.ptt.spzx.feign.product;

import com.ptt.spzx.model.dto.product.SkuSaleDto;
import com.ptt.spzx.model.entity.product.ProductSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: ProductFeignClient
 * Package: com.ptt.spzx.feign.product
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 15:04
 * @Version 1.0
 */
@FeignClient(value = "service-product")  //需要调用的服务  在nacos中注册的应用名
public interface ProductFeignClient {
    //定义远程调用的接口
    @RequestMapping(value="/api/product/getBySkuId/{skuId}")
    public ProductSku getBySkuId(@PathVariable("skuId") Long skuId);
    @PostMapping("/api/product/updateSkuSaleNum")
    public Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList);
}
