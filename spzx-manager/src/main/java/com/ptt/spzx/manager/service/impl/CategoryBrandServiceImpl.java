package com.ptt.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptt.spzx.manager.mapper.CategoryBrandMapper;
import com.ptt.spzx.manager.service.CategoryBrandService;
import com.ptt.spzx.model.dto.product.CategoryBrandDto;
import com.ptt.spzx.model.entity.product.Brand;
import com.ptt.spzx.model.entity.product.CategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CategoryBrandServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 15:31
 * @Version 1.0
 */
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {
    @Autowired
    private CategoryBrandMapper categoryBrandMapper;
    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer size, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(page,size);
        PageInfo<CategoryBrand> pageInfo=new PageInfo<>(categoryBrandMapper.selectByBrandCategory(categoryBrandDto));
        return pageInfo;

    }

    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.insert(categoryBrand);
    }

    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand);

    }

    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id);
    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return categoryBrandMapper.selectBrandByCategoryId(categoryId);

    }
}
