package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: BrandMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/11 14:31
 * @Version 1.0
 */
@Mapper
public interface BrandMapper {
    List<Brand> selectByPage();

    void insert(Brand brand);

    void updateById(Brand brand);

    void deleteById(Integer id);

    List<Brand> selectAll();
}
