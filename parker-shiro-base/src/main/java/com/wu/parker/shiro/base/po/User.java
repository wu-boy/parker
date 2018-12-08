package com.wu.parker.shiro.base.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author: wusq
 * @date: 2018/12/8
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

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    public String getCredentialsSalt(){
        return this.username;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
