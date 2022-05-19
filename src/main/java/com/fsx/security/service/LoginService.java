package com.fsx.security.service;

import com.fsx.security.entity.ResponseResult;
import com.fsx.security.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
