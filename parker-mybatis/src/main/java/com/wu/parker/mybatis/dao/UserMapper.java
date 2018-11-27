package com.wu.parker.mybatis.dao;

import com.wu.parker.mybatis.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: wusq
 * @date: 2018/11/26
 */
@Mapper
public interface UserMapper {

    int save(User user);
}
