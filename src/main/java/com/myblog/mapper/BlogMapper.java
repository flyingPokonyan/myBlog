package com.myblog.mapper;

import com.myblog.entity.Blog;
import com.myblog.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    //新增列表
    int saveBlog(Blog blog);


    //查询文章管理列表
    List<BlogQuery> blogList();

    //删除文章
    int deleteBlog(Long id);

    //根据id查询文章
    BlogShow findById(Long id);

    //修改文章
    int updateBlog(BlogShow blogShow);

    //根据搜索条件搜索文章
    List<BlogQuery> findBySearch(BlogSearch blogSearch);

    //获取首页的最新文章
    List<FirstPageBlog> firstBlogList();

    //获取最新推荐文章
    List<RecommendBlog> recommendBlogList();

    //搜索博客列表
    List<FirstPageBlog> searchBlogList(String search);

    //获取文章总数
    Integer getBlogTotal();

    //获取评论总数
    Integer getCommentTotal();

    //获取浏览总数
    Integer getBlogViewTotal();

    //获取留言总数
    Integer getMessageTotal();

    //获取文章详情内容
    DetailedBlog getDetailedBlog(Long id);

    //更新文章浏览量
    int updateBlogViews(Long id);

    //获取文章评论个数
    int getCommentCountById(Long id);

    //根据分类id查询文章列表
    List<FirstPageBlog> findByTypeId(Long typeId);
}
