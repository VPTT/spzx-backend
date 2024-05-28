package com.ptt.spzx.manager.mapper;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.dto.product.CategoryBrandDto;
import com.ptt.spzx.model.entity.product.Brand;
import com.ptt.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: CategoryBrandMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 15:31
 * @Version 1.0
 */
@Mapper
public interface CategoryBrandMapper {
    List<CategoryBrand> selectByBrandCategory(CategoryBrandDto categoryBrandDto);

    void insert(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> selectBrandByCategoryId(Long categoryId);
}
