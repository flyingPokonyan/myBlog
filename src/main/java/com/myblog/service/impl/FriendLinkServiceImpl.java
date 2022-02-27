package com.myblog.service.impl;

import com.myblog.entity.FriendLink;
import com.myblog.mapper.FriendLinkMapper;
import com.myblog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public int saveFriendLink(FriendLink friendLink) {
        friendLink.setCreateTime(new Date());
        return friendLinkMapper.saveFriendLink(friendLink);
    }

    @Override
    public int deleteFriendLink(Long id) {
        return friendLinkMapper.deleteFriendLink(id);
    }

    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return friendLinkMapper.updateFriendLink(friendLink);
    }

    @Override
    public List<FriendLink> friendLinkList() {
        return friendLinkMapper.friendLinkList();
    }

    @Override
    public FriendLink findByBlogAddress(String blogAddress) {
        return friendLinkMapper.findByBlogAddress(blogAddress);
    }

    @Override
    public FriendLink findById(Long id) {
        return friendLinkMapper.findById(id);
    }
}
