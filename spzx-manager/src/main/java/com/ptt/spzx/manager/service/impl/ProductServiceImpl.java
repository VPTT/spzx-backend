package com.ptt.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptt.spzx.manager.mapper.ProductDetailsMapper;
import com.ptt.spzx.manager.mapper.ProductMapper;
import com.ptt.spzx.manager.mapper.ProductSkuMapper;
import com.ptt.spzx.manager.service.ProductService;
import com.ptt.spzx.model.dto.product.ProductDto;
import com.ptt.spzx.model.entity.product.Category;
import com.ptt.spzx.model.entity.product.Product;
import com.ptt.spzx.model.entity.product.ProductDetails;
import com.ptt.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: ProductServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/12 10:55
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        List<Product> products= productMapper.selectCondition(productDto);
        PageHelper.startPage(page,limit);
        return new PageInfo<Product>(products);

    }

    @Transactional
    @Override
    public void save(Product product) {
        // 保存商品数据
        product.setStatus(0);              // 设置上架状态为0
        product.setAuditStatus(0);         // 设置审核状态为0
        productMapper.save(product);//useGeneratedKeys=true mapper执行完后 会获取主键id 回填id值到product对象中

        // 保存商品sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(int i=0,size=productSkuList.size(); i<size; i++) {

            // 获取ProductSku对象
            ProductSku productSku = productSkuList.get(i);
            productSku.setSkuCode(product.getId() + "_" + i);       // 构建skuCode

            productSku.setProductId(product.getId());               // 设置商品id
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            productSku.setSaleNum(0);                               // 设置销量
            productSku.setStatus(0);
            productSkuMapper.save(productSku);                    // 保存数据

        }

        // 保存商品详情数据
//        ProductDetails productDetails = new ProductDetails();
//        productDetails.setProductId(product.getId());
//        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(product.getId(),product.getDetailsImageUrls());


    }

    @Override
    public Product getById(Integer id) {
        Product product=productMapper.selectById(id);
        List<ProductSku> skuList=productSkuMapper.selectById(id);
        product.setProductSkuList(skuList);
        return product;
    }

    @Override
    public void updateById(Product product) {
        productMapper.updateById(product);
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(ProductSku sku:productSkuList){
            productSkuMapper.updateById(sku);
        }

//        ProductDetails productDetails=productDetailsMapper.selectByProductId(product.getId());
//        productDetails.setImageUrls(product.getDetailsImageUrls());
//        productDetailsMapper.updateById(productDetails);
        productDetailsMapper.updateById(product.getId(),product.getDetailsImageUrls());

    }

    @Override
    public void deleteById(Long id) {
        productMapper.deleteById(id);                   // 根据id删除商品基本数据
        productSkuMapper.deleteByProductId(id);         // 根据商品id删除商品的sku数据
        productDetailsMapper.deleteByProductId(id);     // 根据商品的id删除商品的详情数据
    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if(auditStatus == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        } else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审批不通过");
        }
        productMapper.updateById(product);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if(status == 1) {
            product.setStatus(1);
        } else {
            product.setStatus(-1);
        }
        productMapper.updateById(product);
    }
}
