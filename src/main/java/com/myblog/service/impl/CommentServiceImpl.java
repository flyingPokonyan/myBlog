package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.mapper.BlogMapper;
import com.myblog.mapper.CommentMapper;
import com.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> comments = commentMapper.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        if(comments.size() > 0){
            for(Comment comment:comments){
                Long id = comment.getId();
                String parentNickName = comment.getNickname();
                List<Comment> childComments = commentMapper.findByBlogIdParentIdNotNull(blogId, id);
                combineChildren(parentNickName,childComments,blogId);
                comment.setReplyComments(tempReplys);
                tempReplys = new ArrayList<>();
            }
        }

        return comments;
    }

    //根据传参进来的父评论信息查询子评论
    private void combineChildren(String parentNickName,List<Comment> childComments,Long blogId){
        if(childComments.size() > 0){
            for(Comment comment:childComments){
                String childName = comment.getNickname();
                comment.setParentNickname(parentNickName);
                Long childId = comment.getId();
                tempReplys.add(comment);
                recursively(blogId,childId,childName);
            }
        }
    }

    //查询出二级子评论
    private void recursively(Long blogId, Long childId, String parentNickname1){
        List<Comment> replayComments  = commentMapper.findByBlogIdAndReplayId(blogId, childId);
        if(replayComments.size() > 0){
            for(Comment comment:replayComments){
                String childName = comment.getNickname();
                comment.setParentNickname(parentNickname1);
                Long id = comment.getId();
                tempReplys.add(comment);
                recursively(blogId,id,childName);
            }
        }
    }

    //添加评论
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int i = commentMapper.saveComment(comment);
        //更新文章的评论数量
        blogMapper.getCommentCountById(comment.getId());
        return i;
    }

    //删除评论
    @Override
    public void deleteComment(Comment comment, Long id) {
        commentMapper.deleteComment(id);
        //更新文章的评论数量
        blogMapper.getCommentCountById(comment.getBlogId());
    }
}
