package com.example.de.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.de.dao.UserRepository;
import com.example.de.domain.Order;
import com.example.de.domain.Project;
import com.example.de.domain.User;
import com.example.de.service.OrderService;
import com.example.de.service.ProjectService;
import com.example.de.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    private  Integer duid;
    public OrderController(){

    }
    public OrderController(Integer duid){
        this.duid=duid;
    }

    @PostMapping(value = "/islogin")
    public String isLogin(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session) {
        String str="";
        List<User> users = userRepository.findAll();
        for(int j = 0;j<users.size();j++){
            if(users.get(j).getAccount().equals(account) && users.get(j).getPassword().equals(password)){
                this.duid=users.get(j).getUid();
                str="redirect:/listusers";
                break;
            }if(j==users.size()-1){
                str="loginunsuccess";
                break;
            }
        }
        return str;
    }

    @PostMapping(value = "/ismyplogin")
    public String isMyPLogin(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session) {
        String str="";
        List<User> users = userRepository.findAll();
        for(int j = 0;j<users.size();j++){
            if(users.get(j).getAccount().equals(account) && users.get(j).getPassword().equals(password)){
                this.duid=users.get(j).getUid();
                str="redirect:/myplistusers";
                break;
            }if(j==users.size()-1){
                str="loginunsuccess";
                break;
            }
        }
        return str;
    }

  @RequestMapping("/loginunsuccess")//q登录不成功
  public String LoginUnsuccess(String kw, Model model, Pageable pageable){
    Page<User> ppu=userService.findAll(kw,pageable);
    model.addAttribute("pages",ppu);
    //return "loginsuccess";
    return "login";
  }

    @GetMapping("/buy/{uid}")
    public String buyAProject(@PathVariable("uid") Integer uid,HttpSession session){
        //int auid=(Integer) session.getAttribute("userId");
        Project project=projectService.fingById(uid);
        String p_name=project.getPname();
        String p_script=project.getPscript();
        String p_others=project.getPothers();
        Double p_price=project.getPprice();
        String o_detail="总计1个商品。【1】商品名称："+p_name+";商品价格："+p_price+";商品描述:"+p_script+";商品备注:"+p_others;
        Order order=new Order();
//        LoignUser loignUser=new LoignUser();
//        int theuid=loignUser.getLoginUserId();
//        order.setUid(theuid);
        order.setUid(duid);
        order.setOdetail(o_detail);
        order.setOprice(p_price);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sdf.format(d);
        order.setOdate(sj);
        orderService.save(order);
        //projectService.deleteById(uid);
        return "redirect:/listusers";
    }

    @GetMapping("/del/{uid}")
    public String delAProject(@PathVariable("uid") Integer uid,HttpSession session){
        projectService.deleteById(uid);
        return "redirect:/myplistusers";
    }

    //添加商品操作
    @GetMapping({"/editproject","/editproject/{uid}"})
    public String edit(@PathVariable(name="uid",required = false)Integer uid,Model model){
        Project u=new Project();
        if(uid!=null&&uid>0){
            u=projectService.fingById(uid);
        }
        model.addAttribute("project",u);
        return "editproject";
    }

    //添加商品，保存商品数据到数据库
    @PostMapping("/saveproject")
    public String save(@Validated Project project, BindingResult result)  {
        if(result.hasErrors()){
            return "redirect:/edituser";
        }
        project.setBuyer_id(this.duid);
        projectService.save(project);//存到数据库
        return "redirect:/myplistusers";//返回到控制器 界面
    }

    //@PostMapping("/buys/{pids}")
    @PostMapping("/buys")
    public String deletes(String uids, HttpSession session){
        //int uid=(Integer) session.getAttribute("userId");
        List<Project> projects=new ArrayList<>();
        JSONObject json=JSONObject.parseObject(uids);
        JSONArray arr=json.getJSONArray("uids");
        int ilen=arr.size();
        String o_detail="总计"+ilen+"个商品";
        double sum=0;
        for(int i=0;i<ilen;i++){
            //projects.add(projectService.fingById(arr.getInteger(i)));
            Project project=projectService.fingById(arr.getInteger(i));
            String p_name=project.getPname();
            String p_script=project.getPscript();
            String p_others=project.getPothers();
            Double p_price=project.getPprice();
            String o_detailstr="商品名称："+p_name+";商品价格："+p_price+";商品描述:"+p_script+";商品备注:"+p_others;
            int j=i+1;
            o_detail=o_detail+"【"+j+"】"+o_detailstr+"\n";
            sum=sum+p_price;
        }
        Order order=new Order();
//        LoignUser loignUser=new LoignUser();
//        int theuid=loignUser.getLoginUserId();
//        order.setUid(theuid);
        order.setUid(duid);
        order.setOdetail(o_detail);
        order.setOprice(sum);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sdf.format(d);
        order.setOdate(sj);
        orderService.save(order);
        //projectService.deletes(projects);
        return "redirect:/listusers";

    }

    @RequestMapping("/myplistusers")//请求我的店铺商品页面
    public String myplist(Model model, Pageable pageable){
        int kw=this.duid;
        Page<Project> ppu=projectService.findAllMyProject(kw,pageable);
        model.addAttribute("pages",ppu);
        return "myplistusers";
    }

    @PostMapping("/tooders")//点击按钮，去订单界面
    public String gotoregister()  {
        return "redirect:/orderlistusers";//注册成功界面返回到登陆界面
    }

    @RequestMapping("/orderlistusers")//注解访问路径
    public String list(Model model, Pageable pageable){//前台传过来的pageable
        //if(kw!=null) kw="%"+kw+"%";
        //if(kw==null) kw="%%";
        int kw=this.duid;
        Page<Order> ppu=orderService.findAll(kw,pageable);//默认分页从0页（第一页），曲美也20条数据
        model.addAttribute("pages",ppu);//把取得的的数据发送到前台
        return "orderlistusers";//返回给界面，返回界面名称
    }


}
