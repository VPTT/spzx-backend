package com.ptt.spzx.product.service;

import com.ptt.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * ClassName: BrandService
 * Package: com.ptt.spzx.product.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 15:17
 * @Version 1.0
 */
public interface BrandService {
    List<Brand> findAll();
}
