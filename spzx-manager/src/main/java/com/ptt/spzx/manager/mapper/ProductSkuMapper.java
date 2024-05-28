package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProductSkuMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/12 14:49
 * @Version 1.0
 */
@Mapper
public interface ProductSkuMapper {
    //void save(List<ProductSku> productSkuList);
    void save(ProductSku productSku);

    List<ProductSku> selectById(Integer id);

    void updateById(ProductSku sku);

    void deleteByProductId(Long id);
}
