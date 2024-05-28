package com.ptt.spzx.product.mapper;

import com.ptt.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductDetailsMapper
 * Package: com.ptt.spzx.product.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 16:14
 * @Version 1.0
 */
@Mapper
public interface ProductDetailsMapper {
    ProductDetails selectByProductId(Long productId);
}
