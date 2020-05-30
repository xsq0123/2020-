package com.example.de.service;

import com.example.de.dao.OrderRepository;
import com.example.de.domain.Order;
import com.example.de.domain.OrderDetail;
import com.example.de.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void save(Order o) {
        orderRepository.save(o);
    }

    @Override
    public Page<Order> findAll(int kw, Pageable pageable) {
        return orderRepository.findByKeyword(kw,pageable);
    }

//    @Override
//    public int addOrderInfo(Order o) {
//        return orderRepository.saveOrderInfo(o);
//    }
//
//    @Override
//    public int addOrderDetail(OrderDetail od) {
//        return orderRepository.saveOrderDetail(od);
//    }
}
