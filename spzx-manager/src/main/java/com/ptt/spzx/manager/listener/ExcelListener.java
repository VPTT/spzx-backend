package com.ptt.spzx.manager.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.ptt.spzx.manager.mapper.CategoryMapper;
import com.ptt.spzx.model.vo.product.CategoryExcelVo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * ClassName: ExcelListener
 * Package: com.ptt.spzx.manager.listener
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/10 17:29
 * @Version 1.0
 */
//监听器不能交给spring管理
public class ExcelListener<T> implements ReadListener<T>{
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
 T   */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private CategoryMapper categoryMapper;
    public ExcelListener(CategoryMapper categoryMapper){
        this.categoryMapper=categoryMapper;
    }
    @Override
    public void invoke(T data, AnalysisContext context) {
        //把每行对象data放到cachedDataList集合中
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if(cachedDataList.size() >= BATCH_COUNT){
            saveData();
            cachedDataList=ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }


    }

    //所有操作完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //防止最后读到的数据 没有达到batchSize 没有保存
        saveData();

    }

    private void saveData(){
        categoryMapper.insertBatch((List< CategoryExcelVo >) cachedDataList);
    }
}
