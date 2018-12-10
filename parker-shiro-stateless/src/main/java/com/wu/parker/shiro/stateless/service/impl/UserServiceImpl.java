package com.wu.parker.shiro.stateless.service.impl;

import com.wu.parker.shiro.stateless.dao.UserRepository;
import com.wu.parker.shiro.stateless.po.User;
import com.wu.parker.shiro.stateless.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wusq
 * @date: 2018/12/8
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
