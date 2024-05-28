package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.entity.product.Category;
import com.ptt.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/10 15:46
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    List<Category> selectByParentId(Long parentId);

    List<Category> selectAll();

    void insertBatch(List<CategoryExcelVo> cachedDataList);
}
