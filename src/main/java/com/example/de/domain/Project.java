package com.example.de.domain;
import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity//实现和数据表的映射
@Table(name = "project_info")//指定与哪个表对应
public class Project {

    @Id//主键，自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(length = 30,unique = true)
    @NotNull
    private String pcode;

    @Column(length = 30)
    @NotNull
    private String pname;

    @NotNull
    @Column
    private Double pprice;

    @Column
    //@NotNull
    private String pscript;

    @Column
    //@NotNull
    private String pothers;

    @Column//这是和数据表对应的列
            Integer buyer_id;

    public Integer getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(Integer buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getPothers() {
        return pothers;
    }

    public void setPothers(String pothers) {
        this.pothers = pothers;
    }

    public String getPscript() {
        return pscript;
    }

    public void setPscript(String pscript) {
        this.pscript = pscript;
    }

    public Double getPprice() {
        return pprice;
    }

    public void setPprice(Double pprice) {
        this.pprice = pprice;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
