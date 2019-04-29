package com.wu.parker.feign.client;

import com.wu.parker.common.web.BaseResult;
import com.wu.parker.feign.config.FeignHeadConfig;
import com.wu.parker.feign.pojo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author wusq
 * @date 2019/4/29
 */
@FeignClient(name = "user-service", url = "${feign.client.url}", configuration = FeignHeadConfig.class)
public interface UserClient {

    @GetMapping("get/{id}")
    ResponseEntity<BaseResult> get(@PathVariable("id") String id);

    @GetMapping("list")
    ResponseEntity<BaseResult> list();

    @GetMapping("test")
    ResponseEntity<BaseResult> test();

    @PostMapping("create")
    ResponseEntity<BaseResult> create(@RequestBody User user);

}
