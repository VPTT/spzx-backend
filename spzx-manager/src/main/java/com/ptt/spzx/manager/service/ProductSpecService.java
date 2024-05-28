package com.ptt.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.entity.product.ProductSpec;

import java.util.List;

/**
 * ClassName: ProductSpecService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 17:10
 * @Version 1.0
 */
public interface ProductSpecService {
    PageInfo<ProductSpec> findByPage(Integer page,Integer limit);

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void deleteById(Long id);

    List<ProductSpec> findAll();
}
