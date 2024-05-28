package com.ptt.spzx.manager.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ExcelListener
 * Package: com.ptt.spzx.manager.test
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/10 16:35
 * @Version 1.0
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {
    private List<T> data=new ArrayList<>();

    //读取excel内容 从第二行开始读取 将每行数据封装到T对象
    @Override
    public void invoke(T t, AnalysisContext context) {
        data.add(t);
    }

    //所有操作完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
    public List<T> getData(){
        return data;
    }

}
