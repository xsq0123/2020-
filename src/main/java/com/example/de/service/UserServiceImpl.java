package com.example.de.service;

import com.example.de.dao.UserRepository;
import com.example.de.domain.User;
import com.example.de.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service//自动管理和扫描对象，告诉spring实现了userservice这个接口，作为服务类来提供相关服务
public class UserServiceImpl implements UserService {

    @Autowired//帮我们自动注入需要的实体类对象
    private UserRepository userRepository;
    @Override
    public void save(User u)  {
            userRepository.save(u);
    }

    @Override
    public Page<User> findAll(String kw, Pageable pageable) {
        return userRepository.findByKeyword(kw,pageable);
    }

    @Override
    public User fingById(Integer uid) {
        return userRepository.findById(uid).get();
    }

    @Override
    public void deleteById(Integer uid) {
        userRepository.deleteById(uid);

    }

    @Override
    @Transactional
    public void deletes(List<User> users) {
        for(User u:users){
            userRepository.delete(u);
        }

    }

    /*
    * 检测登陆用户是否合法用户，user登陆用户的账号密码
    * */
    @Override
    public User checkUser(UserLogin user) {
        User u=null;
        Optional<User> ou=userRepository.findByAccount(user.getAccount());//得到用户对象
        //判断是否有这个对象，如果有，获取这个对象,判断它从数据库获得的密码和传进来的密码是否相等
        if(ou.isPresent()){//判断Option中是否包含目标对象
            u=ou.get();
            if(u.getPassword().equals(user.getPassword())){
                return u;//用户合法,返回用户对象
            }
        }
        return null;//返回空对象

    }


}
