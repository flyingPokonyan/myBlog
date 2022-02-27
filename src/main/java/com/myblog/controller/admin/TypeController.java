package com.myblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.entity.Type;
import com.myblog.mapper.BlogMapper;
import com.myblog.queryvo.BlogSearch;
import com.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;
    /**
     * 分页查询分类列表
     * @return
     */
    @GetMapping("/types")
    public String list(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Type> types = typeService.typeList();
        PageInfo<Type> typePageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo",typePageInfo);
        return "admin/types";
    }

    /**
     * 跳转添加窗口
     * @param model
     * @return
     */
    @GetMapping("/openAddType")
    public String openAddWindow(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //跳转修改类型
    @GetMapping("/openEdit/{id}")
    public String openEditWindow(Model model,@PathVariable Long id){
        model.addAttribute("type",typeService.findById(id));
        return "admin/types-input";
    }

    //保存分类
    @PostMapping("/saveType")
    public String saveType(Type type, RedirectAttributes attributes){
        Type byName = typeService.findByName(type.getName());
        if(byName != null){
            attributes.addFlashAttribute("message","请勿添加重复的分类");
            return "redirect:/admin/openAddType";
        }
        if(typeService.saveType(type) != 0){
            attributes.addFlashAttribute("message","添加成功");
        }else{
            attributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/types";
    }


    //修改分类
    @PostMapping("/saveType/{id}")
    public String updateType(Type type,RedirectAttributes attributes){
        Type byName = typeService.findByName(type.getName());
        if(byName != null){
            attributes.addFlashAttribute("message","请勿添加重复的分类");
            return "redirect:/admin/openAddType";
        }
        if(typeService.saveType(type) != 0){
            attributes.addFlashAttribute("message","修改成功");
        }else{
            attributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/types";
    }

    //删除分类
    @GetMapping("/deleteType/{id}")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){
        if(typeService.deleteType(id) != 0){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/types";
    }


}
