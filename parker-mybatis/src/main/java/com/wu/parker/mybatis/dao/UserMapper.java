package com.wu.parker.mybatis.dao;

import com.wu.parker.mybatis.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: wusq
 * @date: 2019/1/10
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

    /**
     * 执行多条SQL语句
     */
    void executeManySql();
}
