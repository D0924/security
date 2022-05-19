package com.fsx.security.controller;

import com.fsx.security.entity.ResponseResult;
import com.fsx.security.entity.User;
import com.fsx.security.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 登入 前端控制器
 * </p>
 *
 * @author Utopia
 * @since 2022-05-18 11:14:52
 */
@RestController
@Slf4j
public class LoginController {

    @Resource
    LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "helloWorld";
    }

    @GetMapping("/helloSecurity")
    @PreAuthorize("hasAuthority('sys:log1')")
    public String helloSecurity() {
        return "helloSecurity";
    }
}
