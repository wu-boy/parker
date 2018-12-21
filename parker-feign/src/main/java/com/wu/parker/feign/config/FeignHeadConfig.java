package com.wu.parker.feign.config;

import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author: wusq
 * @date: 2018/12/21
 */
@Configuration
public class FeignHeadConfig {

    private static final Logger log = LoggerFactory.getLogger(FeignHeadConfig.class);

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String value = request.getHeader(name);

                        // 遍历请求头里面的属性字段，将token添加到新的请求头中转发到下游服务
                        if ("token".equalsIgnoreCase(name)) {
                            //log.debug("添加自定义请求头key:" + name + ",value:" + value);
                            requestTemplate.header(name, value);
                        }
                    }
                } else {
                    log.warn("FeignHeadConfig", "获取请求头失败！");
                }
            }
        };
    }

}
