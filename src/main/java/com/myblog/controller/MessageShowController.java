package com.myblog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.entity.Message;
import com.myblog.entity.User;
import com.myblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageShowController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public String message() {
        return "message";
    }

    //    查询留言
    @GetMapping("/messagecomment")
    public String messages(Model model) {
        List<Message> messages = messageService.messageList();
        model.addAttribute("messages", messages);
        return "message::messageList";
    }

    //添加留言
    @PostMapping("/message")
    public String saveMessage(Model model, Message message, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user != null){
            message.setAdminMessage(true);
            message.setAvatar(user.getAvatar());
        }else{
            message.setAvatar("/images/avator.jpg");
        }
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        int i = messageService.saveMessage(message);
        List<Message> messages = messageService.messageList();
        model.addAttribute("messages", messages);
        return "message::messageList";
    }

    //    删除留言
    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes, Model model){
        messageService.deleteMessage(id);
        return "redirect:/message";
    }

}
