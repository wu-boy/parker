package com.wu.parker.config.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author: wusq
 * @date: 2019/1/10
 */
@Component
@ConfigurationProperties("global-config")
public class GlobalConfig {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
