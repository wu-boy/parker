package com.wu.parker.shiro.stateless.shiro;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问控制过滤器
 * @author wusq
 * @date 2019/3/28
 */
public class StatelessAccessControlFilter extends AccessControlFilter {

    private static final Logger log = LoggerFactory.getLogger(StatelessAccessControlFilter.class);

    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，回往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {

        /**
         * 是在跨域访问的场景下，正式访问之前增加一次预检性质访问，以确定能否正确获取所请求的资源。
         * 会被Shiro拦截
         * 下面代码让Shiro过滤掉OPTIONS请求
         */
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest r = (HttpServletRequest)request;
        String token = r.getHeader("token");
        // 生成无状态Token
        StatelessAuthenticationToken statelessToken = new StatelessAuthenticationToken(token);
        try {
            // 委托给Realm进行登录
            getSubject(request, response).login(statelessToken);
        } catch (Exception e) {
            e.printStackTrace();
            // 登录失败
            onLoginFail(response);
            return Boolean.FALSE;//就直接返回给请求者.
        }
        return Boolean.TRUE;
    }

    /**
     * 登录失败时默认返回401 状态码
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }

}
