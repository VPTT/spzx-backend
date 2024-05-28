package com.ptt.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptt.spzx.manager.mapper.ProductSpecMapper;
import com.ptt.spzx.manager.service.ProductSpecService;
import com.ptt.spzx.model.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProductSpecServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 17:10
 * @Version 1.0
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    private ProductSpecMapper productSpecMapper;
    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<>( productSpecMapper.selectAll() );

    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec) ;
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);
    }

    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.selectAll();
    }
}
