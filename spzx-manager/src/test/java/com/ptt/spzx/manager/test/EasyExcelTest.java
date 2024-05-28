package com.ptt.spzx.manager.test;

import com.alibaba.excel.EasyExcel;
import com.ptt.spzx.model.vo.product.CategoryExcelVo;

import java.util.List;

/**
 * ClassName: EasyExcelTest
 * Package: com.ptt.spzx.manager.test
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/10 16:34
 * @Version 1.0
 */
public class EasyExcelTest {
    public static void main(String[] args) {
        //read();
        write();

    }

    //读操作
    public static List<CategoryExcelVo> read(){
        String fileName="E:\\JAVA学习\\尚品甄选\\test01.xlsx";
        ExcelListener excelListener=new ExcelListener();
        EasyExcel.read(fileName, CategoryExcelVo.class,excelListener).sheet().doRead();
        List<CategoryExcelVo> list=excelListener.getData();

        System.out.println(list);
        return  list;

    }

    public static void write(){
        List<CategoryExcelVo> list=read();
        String fileName="E:\\JAVA学习\\尚品甄选\\test01.xlsx";
        EasyExcel.write(fileName,CategoryExcelVo.class).sheet("分类数据1").doWrite(list);

    }
}
