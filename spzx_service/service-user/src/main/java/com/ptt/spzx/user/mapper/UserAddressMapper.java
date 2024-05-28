package com.ptt.spzx.user.mapper;

import com.ptt.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: UserAddressMapper
 * Package: com.ptt.spzx.user.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 16:20
 * @Version 1.0
 */
@Mapper
public interface UserAddressMapper {
    List<UserAddress> selectByUserId(Long id);

    UserAddress selectById(Long id);
}
