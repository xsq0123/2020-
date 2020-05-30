package com.example.de.dao;

import com.example.de.domain.Order;
import com.example.de.domain.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("select u from Order u where uid=?1 ")
    public Page<Order> findByKeyword(int kw, Pageable pageable);

//    @Insert("insert into order_info(uid,odate,oprice)"+"values(#{uid},#{odate},#{oprice})")
//    @Options(useGeneratedKeys = true,keyProperty = "oid")
//    int saveOrderInfo(Order order);
//
//    @Insert("insert into order_detail(oid,pid,num)"+"values(#{oid},#{pid},#{num})")
//    @Options(useGeneratedKeys = true,keyProperty = "id")
//    int saveOrderDetail(OrderDetail od);


}
