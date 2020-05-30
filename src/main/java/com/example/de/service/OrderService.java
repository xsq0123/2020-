package com.example.de.service;

import com.example.de.domain.Order;
import com.example.de.domain.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
  //保存订单信息
    public void save(Order o) ;
    //查找所有订单
    public Page<Order> findAll(int kw, Pageable pageable);

    //public int addOrderInfo(Order o);
    //public int addOrderDetail(OrderDetail od);
}
