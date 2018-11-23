package com.wu.parker.areadata.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 行政区划数据
 * @author: wusq
 * @date: 2018/11/23
 */
@Entity
@Table(name="area_data_test")
public class AreaData {

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 名称
     */
    @Column(length = 32, nullable = false)
    private String name;

    /**
     * 父节点ID
     */
    @Column(length = 32)
    private String parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
