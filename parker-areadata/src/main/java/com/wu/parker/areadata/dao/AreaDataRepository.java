package com.wu.parker.areadata.dao;

import com.wu.parker.areadata.po.AreaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: wusq
 * @date: 2018/11/23
 */
public interface AreaDataRepository extends JpaRepository<AreaData, String> {

    /**
     * 根据父节点ID查询
     * @param parent
     * @return
     */
    List<AreaData> findByParent(String parent);
}
