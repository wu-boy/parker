package com.wu.parker.feign.client;

import com.wu.parker.common.web.BaseResult;
import com.wu.parker.feign.pojo.entity.User;
import org.springframework.http.ResponseEntity;

/**
 * 容错处理类，当调用失败时，简单返回null
 * @author wusq
 * @date 2019/4/29
 */
public class UserClientFallback implements UserClient{

    @Override
    public ResponseEntity<BaseResult> get(String id) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResult> list() {
        return null;
    }

    @Override
    public ResponseEntity<BaseResult> test() {
        return null;
    }

    @Override
    public ResponseEntity<BaseResult> create(User user) {
        return null;
    }
}
