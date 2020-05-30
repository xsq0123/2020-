package com.example.de.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
/*5.创建用户实体类User
*
* */
@Entity//实现和数据表的映射
@Table(name = "users")//指定与哪个表对应
public class User {

    @Id//主键，自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(length = 30,unique = true)
    @NotNull
    private String account;

    @NotNull
    @Column(length = 30)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}


