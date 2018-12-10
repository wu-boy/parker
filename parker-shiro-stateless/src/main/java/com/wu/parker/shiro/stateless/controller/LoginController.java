package com.wu.parker.shiro.stateless.controller;

import com.google.gson.Gson;
import com.wu.parker.common.encrypt.EncryptUtils;
import com.wu.parker.common.web.BaseResult;
import com.wu.parker.shiro.stateless.constant.RedisConsts;
import com.wu.parker.shiro.stateless.po.User;
import com.wu.parker.shiro.stateless.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: wusq
 * @date: 2018/12/10
 */
@Api(description = "登录服务")
@RestController
@RequestMapping("/login/")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ValueOperations valueOperations;

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @GetMapping("{username}/{password}")
    public BaseResult login(@PathVariable String username, @PathVariable String password){
        BaseResult result = new BaseResult();
        // 此处的密码应该是按照后台的加密规则加密过的，不应该传输明文密码
        try {
            User user = userService.findByUsername(username);
            if(user == null){
                result.setCode(HttpStatus.UNAUTHORIZED.value());
                result.setMessage("用户名或密码错误");
            }else{
                if(password.equals(user.getPassword())){

                    // 生成随机token
                    Date date = new Date();
                    String token = EncryptUtils.md5(user.getUsername(), date.toString());
                    user.setToken(token);

                    // 写入Redis
                    Gson gson = new Gson();
                    valueOperations.set(RedisConsts.USER_TOKEN + token, gson.toJson(user), 4, TimeUnit.HOURS);

                    // 返回给前端
                    result.setData(user);
                }else{
                    result.setCode(HttpStatus.UNAUTHORIZED.value());
                    result.setMessage("用户名或密码错误");
                }
            }
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
