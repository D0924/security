package com.fsx.security.filter;

import com.fsx.security.entity.LoginUser;
import com.fsx.security.utils.JwtUtil;
import com.fsx.security.utils.RedisCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (!Strings.hasText(token)) {
            // 请求头没有携带token信息选择放行
            filterChain.doFilter(request, response);
            return;
        }

        String userId = "";
        try {
            // 解析token
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法!");
        }
        // 从redis中获取用户信息
        LoginUser user = redisCache.getCacheObject("login:" + userId);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户未登录");
        }
        // 将登入信息传到上下文中 提供给后序操作使用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 过滤器放行
        filterChain.doFilter(request, response);
    }
}
