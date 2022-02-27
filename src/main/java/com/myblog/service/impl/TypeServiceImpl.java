package com.myblog.service.impl;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.myblog.entity.Type;
import com.myblog.mapper.TypeMapper;
import com.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> typeList() {
        return typeMapper.typeList();
    }

    @Override
    public Type findById(Long id) {
        return typeMapper.findById(id);
    }

    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteType(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public Type findByName(String name) {
        return typeMapper.findByName(name);
    }

    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeMapper.getAllTypeAndBlog();
    }
}
