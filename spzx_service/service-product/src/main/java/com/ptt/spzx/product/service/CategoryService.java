package com.ptt.spzx.product.service;

import com.ptt.spzx.model.entity.product.Category;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: com.ptt.spzx.product.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/16 15:19
 * @Version 1.0
 */
public interface CategoryService {
    List<Category> findData();

    List<Category> findCategoryTree();
}
