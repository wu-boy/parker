package com.wu.parker.jpamybatis.service.impl;

import com.wu.parker.jpamybatis.dao.UserMapper;
import com.wu.parker.jpamybatis.dao.UserRepository;
import com.wu.parker.jpamybatis.po.User;
import com.wu.parker.jpamybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wusq
 * @date: 2018/11/28
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void testTransaction() {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId("2");
        user2.setUsername("test");
        user2.setPassword("test");
        userMapper.save(user2);

        // 抛出异常则全部回滚，不会保存到数据库
        // throw new RuntimeException();
    }

}
