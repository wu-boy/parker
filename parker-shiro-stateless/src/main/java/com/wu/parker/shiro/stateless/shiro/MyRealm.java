package com.wu.parker.shiro.stateless.shiro;

import com.google.gson.Gson;
import com.wu.parker.shiro.stateless.constant.RedisConsts;
import com.wu.parker.shiro.stateless.po.Permission;
import com.wu.parker.shiro.stateless.po.Role;
import com.wu.parker.shiro.stateless.po.User;
import com.wu.parker.shiro.stateless.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author: wusq
 * @date: 2018/12/10
 */
public class MyRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(AuthorizingRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ValueOperations valueOperations;

    /**
     * 仅支持StatelessToken 类型的Token，
     * 那么如果在StatelessAuthcFilter类中返回的是UsernamePasswordToken，那么将会报如下错误信息：
     * Please ensure that the appropriate Realm implementation is configured correctly or
     * that the realm accepts AuthenticationTokens of this type.StatelessAuthcFilter.isAccessAllowed()
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("授权");
        String token = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = null;
        try {
            String tokenKey = RedisConsts.USER_TOKEN + token;
            if(redisTemplate.hasKey(tokenKey)){
                String jsonStr = (String) valueOperations.get(tokenKey);
                Gson gson = new Gson();
                User user = gson.fromJson(jsonStr, User.class);
                authorizationInfo = new SimpleAuthorizationInfo();
                Role role = user.getRole();
                if(role != null){
                    authorizationInfo.addRole(role.getCode());
                    for(Permission p:role.getPermissionList()){
                        authorizationInfo.addStringPermission(p.getCode());
                    }
                }
            }
        } catch (Exception e) {
            log.error("授权错误{}", e.getMessage());
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        log.info("登录认证");

        String token = (String) authenticationToken.getPrincipal();
        String tokenKey = RedisConsts.USER_TOKEN + token;

        if(StringUtils.isBlank(token) || !redisTemplate.hasKey(tokenKey)){
            throw new UnknownAccountException(); // 没找到帐号
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                token,
                token,
                getName() // realm name
        );
        return authenticationInfo;
    }

}
