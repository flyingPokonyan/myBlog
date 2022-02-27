package com.myblog.service.impl;

import com.myblog.entity.Blog;
import com.myblog.mapper.BlogMapper;
import com.myblog.queryvo.*;
import com.myblog.service.BlogService;
import com.myblog.service.TypeService;
import com.myblog.utiles.MarkDownUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeService typeService;

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogMapper.saveBlog(blog);
    }

    @Override
    public List<BlogQuery> blogList() {
        return blogMapper.blogList();
    }

    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public BlogShow findById(Long id) {
        return blogMapper.findById(id);
    }

    @Override
    public int updateBlog(BlogShow blogShow) {
        blogShow.setUpdateTime(new Date());
        return blogMapper.updateBlog(blogShow);
    }

    @Override
    public List<BlogQuery> findBySearch(BlogSearch blogSearch) {
        List<BlogQuery> bySearch = blogMapper.findBySearch(blogSearch);
        for(int i = 0; i < bySearch.size(); i++){
            if(bySearch.get(i).getType() == null && bySearch.get(0).getTypeId() != null){
                bySearch.get(0).setType(typeService.findById(bySearch.get(0).getTypeId()));
            }
        }
        return bySearch;
    }

    @Override
    public List<FirstPageBlog> firstBlogList() {
        return blogMapper.firstBlogList();
    }

    @Override
    public List<RecommendBlog> recommendBlogList() {
        return blogMapper.recommendBlogList();
    }

    @Override
    public List<FirstPageBlog> searchBlogList(String search) {
        return blogMapper.searchBlogList(search);
    }

    @Override
    public Integer getBlogTotal() {
        return blogMapper.getBlogTotal();
    }

    @Override
    public Integer getCommentTotal() {
        return blogMapper.getCommentTotal();
    }

    @Override
    public Integer getBlogViewTotal() {
        return blogMapper.getBlogViewTotal();
    }

    @Override
    public Integer getMessageTotal() {
        return blogMapper.getMessageTotal();
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) throws NotFoundException {
        System.out.println("getDetailedBlog中id为" + id);
        DetailedBlog detailedBlog = blogMapper.getDetailedBlog(id);
        if(detailedBlog == null){
            throw new NotFoundException("该文章不存在！");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        blogMapper.updateBlogViews(id);
        blogMapper.getCommentCountById(id);
        System.out.println("getDetailedBlog方法执行了" + detailedBlog);
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> findByTypeId(Long typeId) {
        return blogMapper.findByTypeId(typeId);
    }

}
