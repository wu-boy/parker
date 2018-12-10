package com.wu.parker.shiro.stateless.service;

import com.wu.parker.shiro.stateless.po.User;

/**
 * @author: wusq
 * @date: 2018/12/8
 */
public interface UserService {

    User findByUsername(String username);
}
