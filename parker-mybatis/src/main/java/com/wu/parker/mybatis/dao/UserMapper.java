package com.wu.parker.mybatis.dao;

import com.wu.parker.mybatis.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: wusq
 * @date: 2018/11/28
 */
@Mapper
public interface UserMapper {

    /**
     * 保存
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(String[] ids);
}
