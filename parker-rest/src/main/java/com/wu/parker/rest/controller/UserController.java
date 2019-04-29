package com.wu.parker.rest.controller;

import com.wu.parker.common.web.BaseResult;
import com.wu.parker.rest.dto.CreateUserDto;
import com.wu.parker.rest.po.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wusq
 * @date 2019/4/29
 */
@Api(description = "用户服务")
@RestController
@RequestMapping("security/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("新增")
    @PostMapping("create")
    public BaseResult create(@RequestBody @Validated CreateUserDto dto, BindingResult bindingResult){
        BaseResult result = new BaseResult();

        // 校验参数
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            log.error("新增用户参数错误:{}", fieldError.getDefaultMessage());
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage(fieldError.getDefaultMessage());
            return result;
        }

        try {
            User user = new User();
            BeanUtils.copyProperties(dto, user);
            log.info("接收到用户{},{}", user.getUsername(), user.getPassword());
            result.setData(user);
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("新增用户错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("根据ID查询用户")
    @GetMapping("get/{id}")
    public BaseResult get(@PathVariable String id){
        BaseResult result = new BaseResult();
        try {
            User o = new User();
            o.setId("1");
            o.setUsername("admin");
            o.setPassword("123456");

            if("1".equals(id)){
                result.setData(o);
            }
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("根据ID查询用户错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("查询列表")
    @GetMapping("list")
    public BaseResult list(){
        BaseResult result = new BaseResult();
        try {
            List<User> list = new ArrayList<>();
            User o = new User();
            o.setId("1");
            o.setUsername("admin");
            o.setPassword("123456");
            list.add(o);

            User o2 = new User();
            o2.setId("2");
            o2.setUsername("wu");
            o2.setPassword("111111");
            list.add(o2);

            result.setData(list);
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("查询列表错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("测试token")
    @GetMapping("test")
    public BaseResult test(@RequestHeader("token") String token){
        BaseResult result = new BaseResult();
        try {
            System.out.println(token);
            result.setData(token);
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("根据ID查询用户错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
