package com.myblog.controller;

import com.myblog.entity.Comment;
import com.myblog.entity.User;
import com.myblog.queryvo.DetailedBlog;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    private String avatar = "/images/avator.jpg";

    //查询评论列表
    @GetMapping("/comments/{id}")
    public String comments(@PathVariable Long id, Model model){
        System.out.println("该文章的id为" + id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    //新增评论
    @PostMapping("/comments")
    public String saveComment(Comment comment, HttpSession session,Model model){

        Long blogId = comment.getBlogId();
        System.out.println("saveComments文章的id为"+ blogId);
        User user = (User)session.getAttribute("user");
        if(user != null){
            comment.setAdminComment(true);
            comment.setAvatar(user.getAvatar());
        }else{
            comment.setAvatar(avatar);
        }
        if(comment.getParentComment().getId() != null){
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        System.out.println("saveComments方法执行了");
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    //删除评论
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String deleteComment(Comment comment,@PathVariable Long blogId,@PathVariable Long id, Model model) throws NotFoundException {
        commentService.deleteComment(comment,id);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("blog",detailedBlog);
        model.addAttribute("comments",comments);
        return "blog";
    }
}
