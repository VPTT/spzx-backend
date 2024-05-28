package com.ptt.spzx.manager.service.impl;

import com.ptt.spzx.manager.mapper.ProductUnitMapper;
import com.ptt.spzx.manager.service.ProductUnitService;
import com.ptt.spzx.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProductUnitServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/12 14:39
 * @Version 1.0
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    @Autowired
    private ProductUnitMapper productUnitMapper ;

    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll() ;
    }
}
