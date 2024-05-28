package com.ptt.spzx.product.mapper;

import com.ptt.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductMapper
 * Package: com.ptt.spzx.product.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 16:11
 * @Version 1.0
 */
@Mapper
public interface ProductMapper {
    Product selectById(Long productId);
}
