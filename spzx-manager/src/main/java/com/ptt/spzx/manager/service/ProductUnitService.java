package com.ptt.spzx.manager.service;

import com.ptt.spzx.model.entity.base.ProductUnit;

import java.util.List;

/**
 * ClassName: ProductUnitService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/12 14:39
 * @Version 1.0
 */
public interface ProductUnitService {
    public List<ProductUnit> findAll();
}
