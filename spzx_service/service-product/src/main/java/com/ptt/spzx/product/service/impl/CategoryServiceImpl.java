package com.ptt.spzx.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ptt.spzx.model.entity.product.Category;
import com.ptt.spzx.product.mapper.CategoryMapper;
import com.ptt.spzx.product.service.CategoryService;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.ptt.spzx.product.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/16 15:19
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisTemplate<String , String> redisTemplate;
    @Override
    public List<Category> findData() {
        //查询redis中是否有一级分类，有则直接返回
        String categoryOneJson = redisTemplate.opsForValue().get("category:one");
        if(StringUtils.hasText(categoryOneJson )){
            //return (List<Category>) JSON.parseObject(categoryOneJson,Category.class);
            List<Category>categoryList= JSON.parseArray(categoryOneJson,Category.class);
            return categoryList;
        }
        //如果缓存中没有 则查询数据库
        List<Category> categoryList=categoryMapper.selectRoot();
        redisTemplate.opsForValue().set("category:one",JSON.toJSONString( categoryList),7, TimeUnit.DAYS);

        return categoryList;
    }

    @Cacheable(value = "category" , key = "'all'") //redis中的key值 category::all
    @Override
    public List<Category> findCategoryTree() {
        List<Category> categoryList=categoryMapper.selectAll();
        List<Category> categoryOne=categoryList.stream().filter(item -> item.getParentId().longValue()==0).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(categoryOne)){
            categoryOne.forEach( oneCategory -> {
                List<Category>  categoryTwo=categoryList.stream()
                        .filter(item-> item.getParentId().longValue()== oneCategory.getId().longValue())
                        .collect(Collectors.toList());
                oneCategory.setChildren(categoryTwo);

                if(!CollectionUtils.isEmpty(categoryTwo)) {
                    categoryTwo.forEach(twoCategory -> {
                        List<Category> categoryThree = categoryList.stream()
                                .filter(item -> item.getParentId().longValue() ==twoCategory.getId().longValue())
                                .collect(Collectors.toList());
                        twoCategory.setChildren(categoryThree);
                    });
                }
            });
        }
        return categoryOne;

    }
}
