package com.myblog.mapper;

import com.myblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    //查询父级评论
    List<Comment> findByBlogIdParentIdNull(Long blogId,Long parentId);

    //查询一级评论 根据父级评论id查询是否有一级子评论
    List<Comment> findByBlogIdParentIdNotNull(Long blogId,Long id);

    //查询二级评论，根据文章id和一级评论id查询是否有二级评论
    List<Comment> findByBlogIdAndReplayId(Long blogId,Long childId);

    //添加评论
    int saveComment(Comment comment);

    //删除评论
    int deleteComment(Long id);
}
