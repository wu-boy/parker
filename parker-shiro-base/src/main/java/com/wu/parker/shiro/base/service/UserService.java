package com.wu.parker.shiro.base.service;

import com.wu.parker.shiro.base.po.User;

/**
 * @author: wusq
 * @date: 2018/12/8
 */
public interface UserService {

    User findByUsername(String username);
}
