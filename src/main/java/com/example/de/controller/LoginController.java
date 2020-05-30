package com.example.de.controller;

import com.example.de.dao.UserRepository;
import com.example.de.domain.User;
import com.example.de.domain.LoignUser;
import com.example.de.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
* 1.用户输入账号密码，并提交
* 2.控制器获得账号密码，去数据库中比对查找对应的用户信息
* 3.如果用户正确转向主界面，否则回到登录并提示错误
* 为了实现登录，
* 1.定义了一个JavaBean（UserLogin）用来存储登陆时的用户名和密码，分开验证
* 2.在Dao访问中添加通过account查询用户信息的方法，（在UserRespository）service也要添加，
*3.get请求返回登陆表单
* */

//qqqq添加：登录成功
@Controller
public class LoginController {
    public int buid;
    public LoginController(int buid){
        this.buid=buid;
    }

    public LoginController(){

    }
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

//    @RequestMapping("/loginunsuccess")//q登录不成功
//    public String LoginUnsuccess(String kw, Model model, Pageable pageable){
//        Page<User> ppu=userService.findAll(kw,pageable);
//        model.addAttribute("pages",ppu);
//        //return "loginsuccess";
//        return "login";
//    }

    /*
    * 点击登录按钮的处理，若对比用户输入的账号和密码是否与数据库的账号密码一致则跳到成功界面，
    * 否则，提示登录不成功
    * */

}


    /*
    @RequestMapping("/login")//qqq
    public String Login(String kw, Model model, Pageable pageable){
        Page<User> ppu=userService.findAll(kw,pageable);
        model.addAttribute("pages",ppu);
        //return "loginsuccess";
        return "login";
    }

     @RequestMapping("/loginsuccess")//q登录成功
    public String loginSuccess(String kw, Model model, Pageable pageable){
        Page<User> ppu=userService.findAll(kw,pageable);
        model.addAttribute("pages",ppu);
        return "loginsuccess";
    }

    @PostMapping("/dologin")//提交表单,通过session把用户保存起来
    public String dothelogin(@Validated UserLogin user, BindingResult result,
                        HttpSession session, Model model){
        if(result.hasErrors()){
            return "redirect:/loign";
        }
        //检查用户身份方法
        User u=userService.checkUser(user);

        if(u!=null){//判断用户是否存在
            session.setAttribute("user",u);//去数据库中检索用户或修改checkUser返回用户对象
            //return "loginsuccess";//qqq商品主界面
            return "index";
        }
        model.addAttribute("fail","账号或密码不正确");
        return "redirect:/loign";
    }
    * */

    /*
    @GetMapping("/loign")//返回登录表单
    public String login(Model model){
        UserLogin userlogin=new UserLogin();
        model.addAttribute("userlogin",userlogin);
        return "login";
        //return "redirect:login";
    }

    @PostMapping("/dologin")//提交表单,通过session把用户保存起来
    public String dothelogin(@Validated UserLogin user, BindingResult result
                             ){//HttpSession session, Model model
        if(result.hasErrors()){
            return "redirect:/loign";
        }
        //检查用户身份方法
        User u=userService.checkUser(user);

        if(u!=null){//判断用户是否存在
            //session.setAttribute("user",u);//去数据库中检索用户或修改checkUser返回用户对象
            //return "loginsuccess";//qqq商品主界面
            return "redirect:/index";
        }
        //model.addAttribute("fail","账号或密码不正确");
        return "redirect:/loign";
    }
    */

//qqqq添加：登录
/*

 */
