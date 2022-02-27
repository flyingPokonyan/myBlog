package com.myblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.entity.Picture;
import com.myblog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    //分页查询照片列表
    @GetMapping("/pictures")
    public String pictures(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<Picture> pictures = pictureService.pictureList();
        PageHelper.startPage(pageNum,10);
        PageInfo pageInfo = new PageInfo(pictures);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/pictures";
    }

    //跳转到新增照片页面
    @GetMapping("/openAddPicture")
    public String openAddPicture(Model model){
        model.addAttribute("picture",new Picture());
        return "admin/pictures-input";
    }

    //新增照片
    @PostMapping("/savePicture")
    public String savePicture(Picture picture, RedirectAttributes attributes){
        if(pictureService.savePicture(picture) != 0){
            attributes.addFlashAttribute("message","添加成功");
        }else{
            attributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/pictures";
    }

    //跳转到修改照片页面
    @GetMapping("/openEditPicture/{id}")
    public String openEditPicture(Model model,@PathVariable  Long id){
        model.addAttribute("picture",pictureService.findById(id));
        return "admin/pictures-input";
    }

    //修改照片
    @PostMapping("/updatePicture")
    public String updatePicture(RedirectAttributes attributes,Picture picture){
        if(pictureService.updatePicture(picture) != 0){
            attributes.addFlashAttribute("message","修改成功");
        }else{
            attributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/pictures";
    }

    //删除照片
    @GetMapping("/deletePicture/{id}")
    public String deletePicture(@PathVariable  Long id,RedirectAttributes attributes){
        if(pictureService.deletePicture(id) != 0){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/pictures";
    }

}
