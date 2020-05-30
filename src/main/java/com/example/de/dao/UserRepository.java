package com.example.de.dao;

import com.example.de.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


/*
5.用户数据接口实现，
查找用户，修改用户密码
* <UserRepository,Integer>使用什么实体，实体主键的类型
* 访问数据库的基本方法，增删改查
* 精准查询= account like ?1 or email like ?1
 * */
public interface UserRepository extends JpaRepository<User,Integer> {
  //通过id查询用户信息，获取唯一用户
    @Query("select u from User u where account like ?1")
    public Page<User> findByKeyword(String kw, Pageable pageable);

    //通过账号查询用户信息，获取唯一用户
    public Optional<User> findByAccount(String account);

    //错了
   // @Query("update  User u set u.passoord=?1 where  u.uid=?2")
    //public void modifyPassword(String pwd,Integer uid);

}
