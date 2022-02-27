package com.myblog.mapper;

import com.myblog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface UserMapper {

    User findUser(@Param("username") String username,@Param("password") String password);
}
