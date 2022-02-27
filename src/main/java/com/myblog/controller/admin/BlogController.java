package com.myblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.entity.Blog;
import com.myblog.entity.Type;
import com.myblog.entity.User;
import com.myblog.queryvo.BlogQuery;
import com.myblog.queryvo.BlogSearch;
import com.myblog.queryvo.BlogShow;
import com.myblog.queryvo.DetailedBlog;
import com.myblog.service.BlogService;
import com.myblog.service.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/openAddBlog")
    public String openAddWindow(Model model){
        model.addAttribute("types",typeService.typeList());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    //新增博客
    @PostMapping("/saveBlog")
    public String saveBlog(Blog blog, RedirectAttributes attributes, HttpSession session){
        User user = (User)session.getAttribute("user");
        blog.setUser(user);
        blog.setUserId(user.getId());
        blog.setType(typeService.findById(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        if(blogService.saveBlog(blog) != 0){
            attributes.addFlashAttribute("message","添加成功");
        }else{
            attributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/blogs";
    }

    //分页查询文章列表
    @RequestMapping("/blogs")
    public String blogList(Model model,@RequestParam(defaultValue = "1",value="pageNum") Integer pageNum){
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogQuery> blogList = blogService.blogList();
        PageInfo pageInfo = new PageInfo(blogList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",typeService.typeList());
        return "admin/blogs";

    }

    //删除文章
    @GetMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes) {
        if (blogService.deleteBlog(id) != 0) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/blogs";
    }

    //跳转修改文章
    @GetMapping("/openEditBlog/{id}")
    public String openEditBlog(Model model,@PathVariable Long id){
        BlogShow blogById = blogService.findById(id);
        List<Type> allType = typeService.typeList();
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        return "admin/blogs-input";
    }

    //修改文章
    @PostMapping("/editBlog/{id}")
    public String editBlog(BlogShow blogShow,RedirectAttributes attributes){
        int b = blogService.updateBlog(blogShow);
        if(b ==0){
            attributes.addFlashAttribute("message", "修改失败");
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }

    //根据搜索条件搜索文章
    @PostMapping("/searchBlog")
    public String searchBlog(BlogSearch blogSearch, Model model,
                             @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<BlogQuery> bySearch = blogService.findBySearch(blogSearch);
        PageHelper.startPage(pageNum,10);
        PageInfo pageInfo = new PageInfo(bySearch);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs :: blogList";
    }


}
