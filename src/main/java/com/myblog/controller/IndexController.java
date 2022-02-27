package com.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.queryvo.DetailedBlog;
import com.myblog.queryvo.FirstPageBlog;
import com.myblog.queryvo.RecommendBlog;
import com.myblog.service.BlogService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {


    @Autowired
    private BlogService blogService;

    //获取最新推荐文章和最新文章列表
    @GetMapping({"/","","/index"})
    public String index(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<RecommendBlog> recommendBlogs = blogService.recommendBlogList();
        List<FirstPageBlog> firstPageBlogs = blogService.firstBlogList();
        PageInfo pageInfo = new PageInfo(firstPageBlogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendedBlogs",recommendBlogs);
        return "index";
    }

    //搜索博客
    @PostMapping("/search")
    public String searchBlog(@RequestParam String query, Model model,
                             @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
//        if(search == null){
//            model.addAttribute("message","请输入搜索内容");
//            return "index";
//        }
        PageHelper.startPage(pageNum,10);
        List<FirstPageBlog> firstPageBlogs = blogService.searchBlogList(query);
        PageInfo pageInfo = new PageInfo(firstPageBlogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }

    //统计博客信息
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model) {
        int blogTotal = blogService.getBlogTotal();
        int blogViewTotal = blogService.getBlogViewTotal();
        int blogCommentTotal = blogService.getCommentTotal();
        int blogMessageTotal = blogService.getMessageTotal();

        model.addAttribute("blogTotal", blogTotal);
        model.addAttribute("blogViewTotal", blogViewTotal);
        model.addAttribute("blogCommentTotal", blogCommentTotal);
        model.addAttribute("blogMessageTotal", blogMessageTotal);
        return "index :: blogMessage";
    }

    //打开文章详情页
    @GetMapping("/blogs/{id}")
    public String blogs(Model model,@PathVariable Long id) throws NotFoundException {
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        model.addAttribute("blog",detailedBlog);
        return "blog";
    }
}
