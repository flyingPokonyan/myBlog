package com.myblog.service;

import com.myblog.entity.Message;

import java.util.List;

public interface MessageService {

    //查询留言列表
    List<Message> messageList();

    //保存留言
    int saveMessage(Message message);

    //删除留言
    int deleteMessage(Long id);
}
