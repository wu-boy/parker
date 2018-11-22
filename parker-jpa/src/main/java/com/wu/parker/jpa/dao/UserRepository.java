package com.wu.parker.jpa.dao;

import com.wu.parker.jpa.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wusq
 * @date: 2018/11/22
 */
public interface UserRepository extends JpaRepository<User, String> {
}
