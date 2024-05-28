package com.ptt.spzx.product.service.impl;

import com.ptt.spzx.model.entity.product.Brand;
import com.ptt.spzx.product.mapper.BrandMapper;
import com.ptt.spzx.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: BrandServiceImpl
 * Package: com.ptt.spzx.product.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 15:17
 * @Version 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }
}
