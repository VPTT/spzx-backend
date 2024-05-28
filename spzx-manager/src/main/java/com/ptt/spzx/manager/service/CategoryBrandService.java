package com.ptt.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.dto.product.CategoryBrandDto;
import com.ptt.spzx.model.entity.product.Brand;
import com.ptt.spzx.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * ClassName: CategoryBrandService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 15:31
 * @Version 1.0
 */
public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer page, Integer size, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
