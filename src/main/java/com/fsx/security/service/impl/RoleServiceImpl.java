package com.fsx.security.service.impl;

import com.fsx.security.entity.Role;
import com.fsx.security.mapper.RoleMapper;
import com.fsx.security.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Utopia
 * @since 2022-05-18 11:14:52
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
