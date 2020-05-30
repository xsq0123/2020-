package com.example.de.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.de.dao.ProjectRepository;
import com.example.de.domain.Project;
import com.example.de.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping("/listusers")//注解访问路径
    public String list(String kw, Model model, Pageable pageable){//前台传过来的pageable
        if(kw!=null) kw="%"+kw+"%";
        if(kw==null) kw="%%";
        Page<Project> ppu=projectService.findAll(kw,pageable);//默认分页从0页（第一页），曲美也20条数据
        model.addAttribute("pages",ppu);//把取得的的数据发送到前台
        return "listusers";//返回给界面，返回界面名称
    }
}
