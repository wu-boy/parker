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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wusq
 * @date: 2018/12/5
 */
@Api(description = "用户服务")
@RestController
@RequestMapping("security/user/")
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
}
