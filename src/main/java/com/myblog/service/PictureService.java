package com.myblog.service;

import com.myblog.entity.Picture;

import java.util.List;

public interface PictureService {
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
