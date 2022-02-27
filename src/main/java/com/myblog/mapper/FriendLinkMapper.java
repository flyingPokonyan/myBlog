package com.myblog.mapper;

import com.myblog.entity.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendLinkMapper {

    //新增友链
    int saveFriendLink(FriendLink friendLink);

    //删除友链
    int deleteFriendLink(Long id);

    //修改友链
    int updateFriendLink(FriendLink friendLink);

    //获取所有友链
    List<FriendLink> friendLinkList();

    //根据友链地址查询友链
    FriendLink findByBlogAddress(String blogAddress);

    //根据id查询友链
    FriendLink findById(Long id);
}
