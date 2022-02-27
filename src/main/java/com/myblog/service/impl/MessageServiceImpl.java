package com.myblog.service.impl;

import com.myblog.entity.Message;
import com.myblog.mapper.MessageMapper;
import com.myblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    private List<Message> tempReplys = new ArrayList<>();

    //获取留言列表
    @Override
    public List<Message> messageList() {
        List<Message> messageList = messageMapper.findByParentIdNull(Long.parseLong("-1"));
        if(messageList.size() > 0){
            for(Message message:messageList){
                Long id = message.getId();
                String nickName = message.getNickname();
                List<Message> childMessages = messageMapper.findByParentIdNotNull(id);
                combineChildren(childMessages,nickName);
                message.setReplyMessages(tempReplys);
                tempReplys = new ArrayList<>();
            }
        }
        return messageList;
    }

    //根据父留言找出一级子留言
    private void combineChildren(List<Message> childMessages,String nickName){
        if(childMessages != null){
            for(Message message:childMessages){
                message.setParentNickname(nickName);
                //这个message不为空，说明是父留言的子留言，加入到子留言列表中
                tempReplys.add(message);
                Long childId = message.getId();
                String parentNickname = message.getNickname();
                //根据此条留言id查询是否二级子留言
                recursively(childId,parentNickname);
            }
        }
    }

    //根据一级子留言找出二级留言列表并绑定
    private void recursively(Long childId, String parentNickname){
        List<Message> byReplayId = messageMapper.findByReplayId(childId);
        if(byReplayId.size() > 0){
            for(Message message:byReplayId){
                message.setParentNickname(parentNickname);
                tempReplys.add(message);
                Long id = message.getId();
                String nickname = message.getNickname();
                //循环迭代找出子集回复
                recursively(id,nickname);
            }
        }
    }


    @Override
    public int saveMessage(Message message) {
        message.setCreateTime(new Date());
        return messageMapper.saveMessage(message);
    }

    @Override
    public int deleteMessage(Long id) {
        return messageMapper.deleteMessage(id);
    }
}
