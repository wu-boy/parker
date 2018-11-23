package com.wu.parker.areadata.service;

import com.wu.parker.areadata.po.AreaData;

import java.util.List;

/**
 * @author: wusq
 * @date: 2018/11/23
 */
public interface AreaDataService {

    /**
     * 批量保存
     */
    void save();

    /**
     * 根据父节点ID查询
     * @param parent
     * @return
     */
    List<AreaData> list(String parent);
}
