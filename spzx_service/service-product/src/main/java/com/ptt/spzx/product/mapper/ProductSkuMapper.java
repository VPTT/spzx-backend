package com.ptt.spzx.product.mapper;

import com.ptt.spzx.model.dto.h5.ProductSkuDto;
import com.ptt.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: Productmapper
 * Package: com.ptt.spzx.product.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/16 15:20
 * @Version 1.0
 */
@Mapper
public interface ProductSkuMapper {
    List<ProductSku> selectSalesTop();

    List<ProductSku> selectAll(ProductSkuDto productSkuDto);

    ProductSku selectById(Long skuId);

    List<ProductSku> selectByProductID(Long productId);

    void updateSale(Long skuId, Integer num);
}
