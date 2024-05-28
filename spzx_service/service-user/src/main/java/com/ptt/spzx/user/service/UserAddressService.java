package com.ptt.spzx.user.service;

import com.ptt.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
 * ClassName: UserAddressService
 * Package: com.ptt.spzx.user.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 16:20
 * @Version 1.0
 */
public interface UserAddressService {
    List<UserAddress> findUserAddressList();

    UserAddress getUserAddress(Long id);
}
