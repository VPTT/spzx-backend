package com.ptt.spzx.product.service;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.dto.h5.ProductSkuDto;
import com.ptt.spzx.model.dto.product.SkuSaleDto;
import com.ptt.spzx.model.entity.product.ProductSku;
import com.ptt.spzx.model.vo.h5.ProductItemVo;

import java.util.List;

/**
 * ClassName: IndexService
 * Package: com.ptt.spzx.product.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/16 15:17
 * @Version 1.0
 */
public interface ProductService {
    List<ProductSku> findData();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo getItem(Long skuId);

    ProductSku getBySkuId(Long skuId);

    Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList);
}
