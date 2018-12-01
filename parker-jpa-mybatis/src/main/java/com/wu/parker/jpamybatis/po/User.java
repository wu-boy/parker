package com.wu.parker.jpamybatis.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author: wusq
 * @date: 2018/11/22
 */

@Entity
@Table(name="user_test")
public class User {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(length = 32)
    private String id;

    @Column(length = 32, unique = true, nullable = false)
    private String username;

    @Column(length = 32, nullable = false)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
