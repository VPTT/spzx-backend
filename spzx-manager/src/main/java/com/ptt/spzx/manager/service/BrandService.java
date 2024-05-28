package com.ptt.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * ClassName: brandService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 14:30
 * @Version 1.0
 */
public interface BrandService {
    PageInfo<Brand> findByPages(Integer page, Integer size);

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Integer id);

    List<Brand> findAll();
}
