package com.wu.parker.feign.controller;

import com.wu.parker.common.web.BaseResult;
import com.wu.parker.feign.client.UserClient;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wusq
 * @date: 2018/12/21
 */
@RestController
public class FeignContoller {

    private static final Logger log = LoggerFactory.getLogger(FeignContoller.class);

    @Autowired
    private UserClient userClient;

    @ApiOperation("查询用户")
    @GetMapping("get")
    public BaseResult get(){
        BaseResult result = new BaseResult();
        try {
            ResponseEntity<BaseResult> entity = userClient.get("1");
            result = entity.getBody();
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            log.error("查询用户错误{}", e.getMessage());
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
}
