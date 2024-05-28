package com.ptt.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.dto.product.ProductDto;
import com.ptt.spzx.model.entity.product.Product;

/**
 * ClassName: ProductService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/12 10:55
 * @Version 1.0
 */
public interface ProductService {
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    void save(Product product);

    Product getById(Integer id);

    void updateById(Product product);

    void deleteById(Long id);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);
}
