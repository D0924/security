package com.fsx.security.service.impl;

import com.fsx.security.entity.User;
import com.fsx.security.mapper.UserMapper;
import com.fsx.security.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Utopia
 * @since 2022-05-18 11:14:52
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
