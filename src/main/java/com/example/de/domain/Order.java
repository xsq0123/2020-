package com.example.de.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity//实现和数据表的映射
@Table(name = "order_info")//指定与哪个表对应
public class Order {
    @Id//主键，自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;
    @NotNull
    @Column
    private Integer uid;

    @NotNull
    @Column
    private double oprice;

    @NotNull
    @Column
    private String odate;


    @NotNull
    @Column
    private String odetail;

    public String getOdetail() {
        return odetail;
    }

    public void setOdetail(String odetail) {
        this.odetail = odetail;
    }

    public Integer getOid() {
        return oid;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public double getOprice() {
        return oprice;
    }

    public void setOprice(double oprice) {
        this.oprice = oprice;
    }
}
