package com.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.entity.Type;
import com.myblog.queryvo.FirstPageBlog;
import com.myblog.service.BlogService;
import com.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<Type> types = typeService.getAllTypeAndBlog();
        //说明是从导航栏中点击传进来的
        if(id == -1){
            id = types.get(0).getId();
        }
        System.out.println("types中" +id);
        List<FirstPageBlog> byTypeId = blogService.findByTypeId(id);
        PageHelper.startPage(pageNum,10);
        model.addAttribute("types",types);
        System.out.println("types中" + byTypeId);
        PageInfo pageInfo = new PageInfo(byTypeId);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
