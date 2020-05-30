package com.example.de.domain;

import javax.persistence.*;

@Entity//实现和数据表的映射
@Table(name = "order_detail")//指定与哪个表对应
public class OrderDetail {
    @Id//主键，自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int oid;
    @Column
    private int pid;
    @Column
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
