package com.shiro.springbootshiro.mapper;

import com.shiro.springbootshiro.bean.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailMapper {
	Integer addOrderDetail(OrderDetail orderDetail);
	OrderDetail findOrderDetailById(Integer detailId);
	Integer updateOrderDetail(OrderDetail orderDetail);
	List<OrderDetail> findOrderDetailByOrderId(String orderId);
}
