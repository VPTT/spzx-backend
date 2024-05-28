package com.ptt.spzx.product.mapper;

import com.ptt.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: BrandMapper
 * Package: com.ptt.spzx.product.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 15:17
 * @Version 1.0
 */
@Mapper
public interface BrandMapper {
    List<Brand> selectAll();
}
