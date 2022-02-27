package com.myblog.mapper;

import com.myblog.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    //查询父级留言
    List<Message> findByParentIdNull(Long parentId);

    //根据父级留言查询对应的子留言
    List<Message> findByParentIdNotNull(Long parentId);

    //查询二级留言
    List<Message> findByReplayId(Long childId);

    //保存留言
    int saveMessage(Message message);

    //删除留言
    int deleteMessage(Long id);
}
