package com.ptt.spzx.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptt.spzx.common.exception.spzxException;
import com.ptt.spzx.feign.cart.CartFeignClient;
import com.ptt.spzx.feign.product.ProductFeignClient;
import com.ptt.spzx.feign.user.UserFeignClient;
import com.ptt.spzx.model.dto.h5.OrderInfoDto;
import com.ptt.spzx.model.entity.h5.CartInfo;
import com.ptt.spzx.model.entity.order.OrderInfo;
import com.ptt.spzx.model.entity.order.OrderItem;
import com.ptt.spzx.model.entity.order.OrderLog;
import com.ptt.spzx.model.entity.product.ProductSku;
import com.ptt.spzx.model.entity.user.UserAddress;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.h5.TradeVo;
import com.ptt.spzx.order.mapper.OrderInfoMapper;
import com.ptt.spzx.order.mapper.OrderItemMapper;
import com.ptt.spzx.order.mapper.OrderLogMapper;
import com.ptt.spzx.order.service.OrderInfoService;
import com.ptt.spzx.utils.AuthContextUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: OrderInfoServiceImpl
 * Package: com.ptt.spzx.order.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 17:00
 * @Version 1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private CartFeignClient cartFeignClient;
    @Autowired
    private ProductFeignClient productFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderLogMapper orderLogMapper;
    @Override
    public TradeVo getTrade() {
        List<CartInfo> cartInfoList = cartFeignClient.getAllCkecked();
        TradeVo tradeVo=new TradeVo();
        List<OrderItem> orderItemList=new ArrayList<>();
        BigDecimal totalAmount=new BigDecimal(0);
        for(CartInfo cartInfo:cartInfoList){
            OrderItem orderItem=new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);

            totalAmount=totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;



    }

    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        //获取orderItemList
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        if(CollectionUtils.isEmpty(orderItemList)){
            throw new spzxException(ResultCodeEnum.DATA_ERROR);
        }

        //校验商品库存
        for(OrderItem orderItem:orderItemList){
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId());
            if(productSku==null){
                throw new spzxException(ResultCodeEnum.DATA_ERROR);
            }
            if( productSku.getStockNum().intValue() < orderItem.getSkuNum().intValue()){
                throw new spzxException(ResultCodeEnum.STOCK_LESS);
            }
        }

        //添加数据到orderInfo表
        //远程调用 根据地址id获取用户地址
        OrderInfo orderInfo = new OrderInfo();
        //用户id
        orderInfo.setUserId(AuthContextUtil.getUserInfo().getId());
        //订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        //用户昵称
        orderInfo.setNickName(AuthContextUtil.getUserInfo().getNickName());
        //用户收货地址信息


        UserAddress userAddress = userFeignClient.getUserAddress(orderInfoDto.getUserAddressId());

        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());

        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount=totalAmount.add(
                    orderItem.getSkuPrice().multiply( new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);

        orderInfoMapper.insert(orderInfo);


        //添加数据到orderitem表  保存订单明细
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderInfo.getId());
            orderItemMapper.insert(orderItem);
        }

        //记录日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(0);
        orderLog.setNote("提交订单");
        orderLogMapper.insert(orderLog);

        //将订单商品从购物车中删掉
        cartFeignClient.deleteCheked();

        //返回订单id
        return orderInfo.getId();
    }

    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        return orderInfoMapper.selectById(orderId);
    }

    @Override
    public TradeVo buy(Long skuId) {
        ProductSku productSku = productFeignClient.getBySkuId(skuId);
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(skuId);
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setSkuNum(1);
        orderItem.setSkuPrice(productSku.getSalePrice());
        orderItem.setThumbImg(productSku.getThumbImg());
        List<OrderItem> orderItemList=new ArrayList<>();
        orderItemList.add(orderItem);

        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(productSku.getSalePrice());
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;

    }

    @Override
    public PageInfo<OrderInfo> getOrderList(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page,limit);
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<OrderInfo> orderInfoList=orderInfoMapper.selectByStatus(userId, orderStatus);

        orderInfoList.forEach(
                orderInfo -> {
                    List<OrderItem> orderItemList=orderItemMapper.selectByOrderId(orderInfo.getId());
                    orderInfo.setOrderItemList(orderItemList);

                }
        );

        return new PageInfo<>(orderInfoList);


    }

    @Override
    public OrderInfo getOrderInfoByOrderNo(String orderNo) {
        OrderInfo orderInfo=orderInfoMapper.selectByOrderNo(orderNo);
        orderInfo.setOrderItemList( orderItemMapper.selectByOrderId(orderInfo.getId()));
        return orderInfo;

    }

    @Override
    public void updateOrderStatus(String orderNo, Integer orderStatus) {
        OrderInfo orderInfo = orderInfoMapper.selectByOrderNo(orderNo);
        orderInfo.setOrderStatus(1);
        orderInfo.setPayType(orderStatus);
        orderInfo.setPaymentTime(new Date());
        orderInfoMapper.updateById(orderInfo);

        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(1);
        orderLog.setNote("支付宝支付成功");
        orderLogMapper.insert(orderLog);

    }
}
