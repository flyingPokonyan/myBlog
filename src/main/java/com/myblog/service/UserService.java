package com.myblog.service;

import com.myblog.entity.User;

public interface UserService {
    User findUser(String username, String password);
}
