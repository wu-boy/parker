package com.wu.parker.shiro.base.controller;

import com.wu.parker.shiro.base.po.User;
import com.wu.parker.shiro.base.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wusq
 * @date: 2018/12/8
 */
@Api(description = "登录服务")
@RestController
@RequestMapping("/login/")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @GetMapping("{username}/{password}")
    public User login(@PathVariable String username, @PathVariable String password){
        User result = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            result = userService.findByUsername(username);
        } catch (UnknownAccountException e) {
            log.error("用户名或密码错误");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            log.error("用户名或密码错误");
            e.printStackTrace();
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            log.error("其他错误");
            e.printStackTrace();
        }
        return result;
    }
}
