package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.dto.product.ProductDto;
import com.ptt.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProductMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/12 10:55
 * @Version 1.0
 */
@Mapper
public interface ProductMapper {
    public List<Product> selectCondition(ProductDto productDto) ;

    void save(Product product);

    Product selectById(Integer id);

    void updateById(Product product);

    void deleteById(Long id);
}
