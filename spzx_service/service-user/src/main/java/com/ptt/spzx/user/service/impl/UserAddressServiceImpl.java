package com.ptt.spzx.user.service.impl;

import com.ptt.spzx.model.entity.user.UserAddress;
import com.ptt.spzx.user.mapper.UserAddressMapper;
import com.ptt.spzx.user.service.UserAddressService;
import com.ptt.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: UserAddressServiceImpl
 * Package: com.ptt.spzx.user.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 16:20
 * @Version 1.0
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Override
    public List<UserAddress> findUserAddressList() {
        Long id = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.selectByUserId(id);
    }

    @Override
    public UserAddress getUserAddress(Long id) {
        return userAddressMapper.selectById(id);

    }
}
