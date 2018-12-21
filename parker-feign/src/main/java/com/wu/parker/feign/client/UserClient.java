package com.wu.parker.feign.client;

import com.wu.parker.common.web.BaseResult;
import com.wu.parker.feign.config.FeignHeadConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wusq
 * @date: 2018/12/20
 */
@FeignClient(name = "user-service", url = "${feign.client.url}", configuration = FeignHeadConfig.class)
public interface UserClient {

    @GetMapping("get/{id}")
    ResponseEntity<BaseResult> get(@PathVariable("id") String id);

    @GetMapping("test")
    ResponseEntity<BaseResult> test();

    /**
     * 容错处理类，当调用失败时，简单返回null
     */
    @Component
    public class DefaultFallback implements UserClient {

        @Override
        public ResponseEntity<BaseResult> get(@PathVariable("id") String id){
            return null;
        }

        @Override
        public ResponseEntity<BaseResult> test(){
            return null;
        }
    }
}
