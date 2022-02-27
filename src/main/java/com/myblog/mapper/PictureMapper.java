package com.myblog.mapper;

import com.myblog.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureMapper {

    //查询照片列表
    List<Picture> pictureList();

    //根据id查询照片
    Picture findById(Long id);

    //新增照片
    int savePicture(Picture picture);

    //修改照片
    int updatePicture(Picture picture);

    //删除照片
    int deletePicture(Long id);
}
