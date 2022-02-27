package com.myblog.service.impl;

import com.myblog.entity.Picture;
import com.myblog.mapper.PictureMapper;
import com.myblog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<Picture> pictureList() {
        return pictureMapper.pictureList();
    }

    @Override
    public Picture findById(Long id) {
        return pictureMapper.findById(id);
    }

    @Override
    public int savePicture(Picture picture) {
        return pictureMapper.savePicture(picture);
    }

    @Override
    public int updatePicture(Picture picture) {
        return pictureMapper.updatePicture(picture);
    }

    @Override
    public int deletePicture(Long id) {
        return pictureMapper.deletePicture(id);
    }
}
