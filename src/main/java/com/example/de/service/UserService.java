package com.example.de.service;

import com.example.de.domain.User;
import com.example.de.domain.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/*
6.1用户服务接口实现
* 保存实体
* */
public interface UserService {
    public void save(User u) ;
    public Page<User> findAll(String kw, Pageable pageable);
    public User fingById(Integer uid);
    public  void deleteById(Integer uid);
    public  void deletes(List<User> users);
    public  User checkUser(UserLogin user);

}
