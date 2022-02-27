package com.myblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.entity.FriendLink;
import com.myblog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    //查询所有友链
    @GetMapping("/friendLinks")
    public String friendLinks(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<FriendLink> friendLinks = friendLinkService.friendLinkList();
        PageInfo pageInfo = new PageInfo(friendLinks);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendlinks";
    }

    //跳转到添加友链页面
    @GetMapping("/openAddFriendLink")
    public String opendAddFriendLink(Model model){
        model.addAttribute("friendlink",new FriendLink());
        return "admin/friendlinks-input";
    }

    @PostMapping("/saveFriendLink")
    public String saveFriendLink(FriendLink friendLink,RedirectAttributes attributes){
        if(friendLinkService.findById(friendLink.getId()) != null || friendLinkService.findByBlogAddress(friendLink.getBlogaddress()) != null){
            attributes.addFlashAttribute("message","该网站我已经保存啦，请不要申请重复的哦");
            return "redirect:/admin/openAddFriendLink";
        }
        if(friendLinkService.saveFriendLink(friendLink) != 0){
            attributes.addFlashAttribute("message","添加成功");
        }else{
            attributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/friendLinks";
    }

    //跳转修改友链页面
    @GetMapping("/openEditFriendLink/{id}")
    public String openEditFriendLink(Model model,@PathVariable Long id){
        model.addAttribute("friendlink",friendLinkService.findById(id));
        return "admin/friendlinks-input";
    }

    //修改友链
    @PostMapping("/updateFriendLink/{id}")
    public String updateFriendLink(FriendLink friendLink,RedirectAttributes attributes){
        if(friendLinkService.updateFriendLink(friendLink) != 0){
            attributes.addFlashAttribute("message", "编辑成功");
        } else {
            attributes.addFlashAttribute("message", "编辑失败");
        }
        return "redirect:/admin/friendLinks";
    }

    //删除友链
    @GetMapping("/deleteFriendLink/{id}")
    public String deleteFriendLink(@PathVariable Long id,RedirectAttributes attributes){
        if(friendLinkService.deleteFriendLink(id) != 0){
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/friendLinks";
    }

}
