package com.wu.parker.shiro.stateless.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 用于授权的Token对象，类似于Shiro的UsernamePasswordToken
 * @author: wusq
 * @date: 2018/12/10
 */
public class StatelessAuthenticationToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String token;

    public StatelessAuthenticationToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
