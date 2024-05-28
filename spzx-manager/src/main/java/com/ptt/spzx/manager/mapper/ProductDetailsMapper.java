package com.ptt.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: ProductDetailsMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/12 14:49
 * @Version 1.0
 */
@Mapper
public interface ProductDetailsMapper {
    void save(@Param("id") Long id, @Param("imageUrls")String detailsImageUrls);

    void updateById(@Param("id") Long id,@Param("imageUrls") String detailsImageUrls);

    void deleteByProductId(Long id);
}
