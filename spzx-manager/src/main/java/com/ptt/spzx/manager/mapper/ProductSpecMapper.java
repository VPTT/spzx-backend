package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProductSpecMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 17:13
 * @Version 1.0
 */
@Mapper
public interface ProductSpecMapper {
    List<ProductSpec> selectAll();

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void deleteById(Long id);
}
