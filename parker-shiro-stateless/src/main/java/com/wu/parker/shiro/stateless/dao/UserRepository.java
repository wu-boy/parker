package com.wu.parker.shiro.stateless.dao;

import com.wu.parker.shiro.stateless.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wusq
 * @date: 2018/12/8
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
