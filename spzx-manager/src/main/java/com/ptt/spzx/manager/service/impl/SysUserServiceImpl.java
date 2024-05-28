package com.ptt.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptt.spzx.manager.mapper.SysRoleUserMapper;
import com.ptt.spzx.manager.mapper.SysUserMapper;
import com.ptt.spzx.manager.service.SysUserService;
import com.ptt.spzx.model.dto.system.AssginRoleDto;
import com.ptt.spzx.model.dto.system.LoginDto;
import com.ptt.spzx.model.dto.system.SysUserDto;
import com.ptt.spzx.model.entity.system.SysUser;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SysUserServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/28 17:27
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;



    @Override
    public Result login(LoginDto loginDto) {
        //验证码验证
        //获取输入验证码和存储到redis的key名称
        String captcha = loginDto.getCaptcha();//验证码值
        String codeKey = loginDto.getCodeKey();//存储key

        //根据key 查询redis中的验证码
        String redisCode = redisTemplate.opsForValue().get("user:validate" + codeKey);
        //比较验证码 //user:validate"+key
        if(StrUtil.isBlankIfStr(redisCode) || !StrUtil.equalsIgnoreCase( redisCode,captcha)){
            return Result.build(null,ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //一致，则删除该验证码
        redisTemplate.delete("user:validate" + codeKey);

        //用户名密码验证
         SysUser sysUser= sysUserMapper.selectByUserName(loginDto.getUserName());
         if(sysUser==null){
             return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
         }
         if(! DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()).equals(sysUser.getPassword())){
             return Result.build(null,ResultCodeEnum.LOGIN_ERROR);
         }
         String token = UUID.randomUUID().toString().replace("-", "");
        //把登录信息放到redis中
         redisTemplate.opsForValue().set("user:login:" + token , JSON.toJSONString(sysUser) , 7 , TimeUnit.DAYS);

        LoginVo loginVo=new LoginVo();
        loginVo.setToken(token);

        return Result.build(loginVo,ResultCodeEnum.SUCCESS);
     }

    @Override
    public Result getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
         SysUser sysUser= JSON.parseObject(userJson,SysUser.class);
        return Result.build(sysUser,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result logout(String token) {

        redisTemplate.delete("user:login:" + token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> users=sysUserMapper.selectAll(sysUserDto);
        PageInfo<SysUser> pageInfo=new PageInfo<>(users);
        return Result.build(pageInfo,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result saveSysUser(SysUser sysUser) {
        //判断用户名不能重复
        SysUser user = sysUserMapper.selectByUserName(sysUser.getUserName());
        if(user!=null)
        {
            return Result.build(null,ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //密码加密
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        //设置状态值 用户可用-1
        sysUser.setStatus(1);

        sysUserMapper.insert(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result updateSysUser(SysUser sysUser) {
        sysUserMapper.update(sysUser);

        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result deleteById(Integer userId) {

        sysUserMapper.deleteById(userId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result doAssign(AssginRoleDto roleDto) {
        //重新分配角色
        //1.删掉之前的角色
        sysRoleUserMapper.deleteByUserId(roleDto.getUserId());
        //2.添加新的角色
        List<Long> roleList=roleDto.getRoleIdList();
        for(int i=0;i<roleList.size();i++)
        {
            sysRoleUserMapper.insert(roleDto.getUserId(),roleList.get(i));

        }
        return Result.build(null,ResultCodeEnum.SUCCESS);



    }
}
