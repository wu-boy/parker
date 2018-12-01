package com.wu.parker.jpamybatis.dao;

import com.wu.parker.jpamybatis.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wusq
 * @date: 2018/11/22
 */
public interface UserRepository extends JpaRepository<User, String> {
}
