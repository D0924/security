package com.fsx.security.mapper;

import com.fsx.security.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Utopia
 * @since 2022-05-18 11:14:52
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
