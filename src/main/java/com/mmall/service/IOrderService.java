package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.vo.OrderVo;

import java.util.Map;

/**
 * @author ZZH
 * @date 2018/5/4 0004 15:05
 **/
public interface IOrderService {

    ServerResponse pay(Long orderNo,Integer userId,String path);

    ServerResponse aliCallback(Map<String , String> params);

    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);

    ServerResponse createOrder(Integer userId,Integer shippingId);

    ServerResponse<String> cancel(Integer userId,Long orderNo);

    ServerResponse getOrderCartProduct(Integer userId);

    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    ServerResponse<PageInfo> getOrderList(Integer userId ,Integer pageNum ,Integer pageSize);

    ServerResponse<PageInfo> manageList(Integer pageNum ,Integer pageSize);

    ServerResponse<OrderVo> manageDetail(Long orderNo);

    ServerResponse<PageInfo> manageSearch(Long orderNo,Integer pageNum ,Integer pageSize);

    ServerResponse<String> manageSendGoods(Long orderNo);


}
