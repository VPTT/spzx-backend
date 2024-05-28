package com.ptt.spzx.user.controller;

import com.ptt.spzx.model.entity.user.UserAddress;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.user.service.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: UserAddressController
 * Package: com.ptt.spzx.user.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 16:19
 *
 * @Version 1.0
 */
@Tag(name = "用户地址接口")
@RestController
@RequestMapping(value="/api/user/userAddress")
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;
    @Operation(summary = "获取用户地址列表")
    @GetMapping("auth/findUserAddressList")
    public Result findUserAddressList(){
        List<UserAddress> addressList=userAddressService.findUserAddressList();
        return Result.build(addressList, ResultCodeEnum.SUCCESS);

    }

    @GetMapping("getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable("id") Long id){
        return userAddressService.getUserAddress(id);


    }
}
