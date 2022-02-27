package com.myblog.mapper;


import com.myblog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {

    /**
     * 查询所有分类
     * @return
     */
    List<Type> typeList();

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    Type findById(Long id);

    /**
     * 添加分类
     * @param type
     * @return
     */
    int saveType(Type type);

    //删除分类
    int deleteType(Long id);

    //修改分类
    int updateType(Type type);

    //根据分类名查询分类
    Type findByName(String name);

    //查询所有分类和该分类下文章的数量
    List<Type> getAllTypeAndBlog();


}
