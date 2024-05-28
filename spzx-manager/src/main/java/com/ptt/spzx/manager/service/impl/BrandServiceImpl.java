package com.ptt.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptt.spzx.manager.mapper.BrandMapper;
import com.ptt.spzx.manager.service.BrandService;
import com.ptt.spzx.model.entity.product.Brand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: BrandServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 14:31
 * @Version 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public PageInfo<Brand> findByPages(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Brand> brands=brandMapper.selectByPage();
        return new PageInfo<>(brands);

    }

    @Override
    public void save(Brand brand) {
        brandMapper.insert(brand);

    }

    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand);
    }

    @Override
    public void deleteById(Integer id) {
        brandMapper.deleteById(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }
}
