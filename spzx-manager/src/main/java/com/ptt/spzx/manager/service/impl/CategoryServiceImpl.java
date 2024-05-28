package com.ptt.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.ptt.spzx.common.exception.spzxException;
import com.ptt.spzx.manager.listener.ExcelListener;
import com.ptt.spzx.manager.mapper.CategoryMapper;
import com.ptt.spzx.manager.service.CategoryService;
import com.ptt.spzx.model.entity.product.Category;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/10 15:45
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findByParentId(Long parentId) {
        List<Category> categories= categoryMapper.selectByParentId(parentId);
        if(!CollectionUtils.isEmpty( categories)){
            for(Category category:categories){
                if( CollectionUtils.isEmpty (categoryMapper.selectByParentId(category.getId()))){
                    category.setHasChildren(false);

                }
                else {
                    category.setHasChildren(true);
                }

            }
        }
        return categories;


    }

    @Override
    public void exportData(HttpServletResponse response) {
        //设置相应头信息和其他信息
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName= URLEncoder.encode("商品种类","UTF-8");
            //让文件以下载方式打开
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            List<Category> categoryList=categoryMapper.selectAll();
            List<CategoryExcelVo> categoryExcelVoList=new ArrayList<>();
            //List<Category>  -》 List<CategoryExcelVo>
            for(Category category:categoryList){
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category,categoryExcelVo);
                categoryExcelVoList.add(categoryExcelVo);
            }

            EasyExcel.write(response.getOutputStream(),CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);


        } catch (Exception e) {
            e.printStackTrace();
            throw new spzxException(ResultCodeEnum.DATA_ERROR);
        }

    }

    @Override
    public void importData(MultipartFile file) {
        try {
            ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>(categoryMapper);
            EasyExcel.read(file.getInputStream(),CategoryExcelVo.class,excelListener).sheet().doRead();


        } catch (IOException e) {
            e.printStackTrace();
            throw new spzxException(ResultCodeEnum.DATA_ERROR);
        }

    }
}
