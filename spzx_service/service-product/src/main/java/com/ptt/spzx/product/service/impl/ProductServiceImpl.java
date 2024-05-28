package com.ptt.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.dto.h5.ProductSkuDto;
import com.ptt.spzx.model.dto.product.SkuSaleDto;
import com.ptt.spzx.model.entity.product.Product;
import com.ptt.spzx.model.entity.product.ProductDetails;
import com.ptt.spzx.model.entity.product.ProductSku;
import com.ptt.spzx.model.vo.h5.ProductItemVo;
import com.ptt.spzx.product.mapper.ProductDetailsMapper;
import com.ptt.spzx.product.mapper.ProductMapper;
import com.ptt.spzx.product.mapper.ProductSkuMapper;
import com.ptt.spzx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: IndexServiceImpl
 * Package: com.ptt.spzx.product.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/16 15:17
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Override
    public List<ProductSku> findData() {
        return productSkuMapper.selectSalesTop();
    }

    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page,limit);
        List<ProductSku>productList= productSkuMapper.selectAll(productSkuDto);
        PageInfo<ProductSku>pageInfo=new PageInfo<>(productList);
        return pageInfo;

    }

    @Override
    public ProductItemVo getItem(Long skuId) {
        ProductItemVo productItemVo=new ProductItemVo();
        //当前sku信息
        ProductSku productSku= productSkuMapper.selectById(skuId);

        //当前产品信息
        Product product=productMapper.selectById(productSku.getProductId());

        //当前产品的所有sku信息
        List<ProductSku> productSkuList= productSkuMapper.selectByProductID(productSku.getProductId());

        //建立sku信息和skuid的对应关系
        Map<String,Object> skuSpecValueMap=new HashMap<>();
        productSkuList.forEach(item -> {skuSpecValueMap.put(item.getSkuSpec(),item.getId());});

        //商品详细信息
        ProductDetails productDetails= productDetailsMapper.selectByProductId(productSku.getProductId());

        productItemVo.setProductSku(productSku);
        productItemVo.setProduct(product);
        productItemVo.setSliderUrlList(Arrays.asList( product.getSliderUrls().split(",") ) );
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        productItemVo.setSpecValueList(JSON.parseArray( product.getSpecValue()));
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);

        return productItemVo;
    }

    @Override
    public ProductSku getBySkuId(Long skuId) {
        return productSkuMapper.selectById(skuId);

    }

    @Transactional
    @Override
    public Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList) {
        if(!CollectionUtils.isEmpty(skuSaleDtoList)){
            for(SkuSaleDto saleDto:skuSaleDtoList){
                productSkuMapper.updateSale(saleDto.getSkuId(),saleDto.getNum());
            }

        }
        return true;


    }
}
