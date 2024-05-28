package com.ptt.spzx.product.mapper;

import com.ptt.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: com.ptt.spzx.product.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/16 15:20
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    List<Category> selectRoot();

    List<Category> selectAll();
}
