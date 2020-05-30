package com.example.de.controller;

import com.example.de.domain.User;
import com.example.de.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*6.2
* spring mvc控制器：进行相关操作的转发，用户发送一个编辑数据的请求，等我们获得编辑的数据时，
* 去数据库查询相关信息，然后把查询结果反馈到前台，
* 1.查询所有的符合条件的用户信息，并分页传给界面
* 2.编辑方法从数据库中查询需要的数据
* 3.存储方法把新增的用户或编辑后用户信息存储到数据库
* 4.删除用户
* 5.锁定用户，有效状态
* */

@Controller
public class UserController {
    @Autowired//获取服务对象
    private UserService userService;

    //qqqq添加：注册用户界面
    @RequestMapping("/edituser")//注解访问路径
    public String editUser(String kw, Model model, Pageable pageable){//前台传过来的pageable
        Page<User> ppu=userService.findAll(kw,pageable);//默认分页从0页（第一页），曲美也20条数据
        model.addAttribute("pages",ppu);//把取得的的数据发送到前台
        return "edituser";//返回给界面，返回界面名称
    }

    @RequestMapping("/editproject")//注解访问路径
    public String editProject(String kw, Model model, Pageable pageable){//前台传过来的pageable
        Page<User> ppu=userService.findAll(kw,pageable);//默认分页从0页（第一页），曲美也20条数据
        model.addAttribute("pages",ppu);//把取得的的数据发送到前台
        return "editproject";//返回给界面，返回界面名称
    }

    //qqqq添加：注册成功界面
    @RequestMapping("/registersuccess")//注解访问路径
    public String toRegistersuccess(String kw, Model model, Pageable pageable){
        Page<User> ppu=userService.findAll(kw,pageable);
        model.addAttribute("pages",ppu);
        return "registersuccess";
    }

    //qqqq添加：请求登录界面
    @RequestMapping("/rtol")//注解访问路径
    public String rToL(String kw, Model model, Pageable pageable){
        Page<User> ppu=userService.findAll(kw,pageable);
        model.addAttribute("pages",ppu);
        return "rtol";
    }

    //qqqq添加：登录成功界面
    @RequestMapping("/index")//注解访问路径
    public String toIndex(String kw, Model model, Pageable pageable){
        Page<User> ppu=userService.findAll(kw,pageable);//
        model.addAttribute("pages",ppu);//
        return "index";//返回给界面，返回界面名称
    }

    //注册用户
    @GetMapping({"/edituser","/edituser/{uid}"})
    public String edit(@PathVariable(name="uid",required = false)Integer uid,Model model){
        User u=new User();
        if(uid!=null&&uid>0){
            u=userService.fingById(uid);
        }
        model.addAttribute("user",u);
        return "edituser";
    }

    //注册用户，保存用户账号密码到数据库
    @PostMapping("/saveuser")
    public String save(@Validated User user, BindingResult result)  {
            if(result.hasErrors()){
                return "redirect:/edituser";
            }
            userService.save(user);//存到数据库
            return "redirect:/registersuccess";//返回到控制器 界面
    }

    @PostMapping("/tologin")//点击注册按钮，去注册界面
    public String toL()  {
        return "redirect:/rtol";//注册成功界面返回到登陆界面
    }

    @PostMapping("/gotoregister")//点击注册按钮，去注册界面
    public String gotoregister()  {
        return "redirect:/edituser";//注册成功界面返回到登陆界面
    }

    @PostMapping("/cancelregister")//取消注册，返回登陆界面
    public String cancelRegister()  {
        return "redirect:/rtol";//注册界面点击取消按钮返回到
    }

}
