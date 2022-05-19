package com.fsx.security.service.impl;

import com.fsx.security.entity.LoginUser;
import com.fsx.security.entity.ResponseResult;
import com.fsx.security.entity.User;
import com.fsx.security.service.LoginService;
import com.fsx.security.utils.JwtUtil;
import com.fsx.security.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    RedisCache redisCache;
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登入失败!");
        }
        // 认证通过 生成token 存入Redis
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String useId = loginUser.getUser().getId().toString();
        String token = JwtUtil.createJWT(useId);
        redisCache.setCacheObject("login:" + useId, loginUser);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return new ResponseResult<>(200, "登入成功!", map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser user = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        // 获取key
        String id = user.getUser().getId().toString();
        // 删除redis 中的数据
        redisCache.deleteObject("login:" + id);
        return new ResponseResult(200, "注销成功");
    }
}
