package com.fsx.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fsx.security.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Utopia
 * @since 2022-05-18 11:14:52
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    // 使用 userId 查询所拥有的 权限
    List<String> selectPermsByUserId(@Param("userId") Long userId);
}
