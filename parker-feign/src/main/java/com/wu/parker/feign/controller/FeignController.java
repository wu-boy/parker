package com.wu.parker.feign.controller;

import com.wu.parker.common.web.BaseResult;
import com.wu.parker.feign.client.UserClient;
import com.wu.parker.feign.pojo.entity.User;
import com.wu.parker.feign.util.FeignUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author wusq
 * @date 2019/4/29
 */
@Api(description = "Feign测试服务")
@RestController
public class FeignController {

    private static final Logger log = LoggerFactory.getLogger(FeignController.class);

    @Autowired
    private UserClient userClient;

    @ApiOperation("根据ID查询")
    @GetMapping("get/{id}")
    public BaseResult get(@PathVariable String id){
        BaseResult result = new BaseResult();
        try {
            User user = FeignUtils.get(userClient, id);
            System.out.println(user.getId() + user.getUsername() + user.getPassword());
            result.setData(user);
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("根据ID查询错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("查询列表")
    @GetMapping("list")
    public BaseResult list(){
        BaseResult result = new BaseResult();
        try {
            result.setData(FeignUtils.list(userClient));
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
            ResponseEntity<BaseResult> entity = userClient.test();
            result = entity.getBody();
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("测试token错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("新增")
    @PostMapping("create")
    public BaseResult create(@RequestBody User user){
        BaseResult result = new BaseResult();
        try {
            result.setData(FeignUtils.create(userClient, user));
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("新增错误{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
