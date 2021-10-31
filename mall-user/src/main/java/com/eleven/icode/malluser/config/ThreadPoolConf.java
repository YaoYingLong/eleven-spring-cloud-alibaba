package com.eleven.icode.malluser.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author by YingLong on 2021/10/27
 */
@Component
@ConfigurationProperties(prefix = "eleven.task")
public class ThreadPoolConf implements InitializingBean {
    private Integer core;
    private Integer max;

    public Integer getCore() {
        return core;
    }

    public void setCore(Integer core) {
        this.core = core;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "ThreadPoolConf{" +
                "core=" + core +
                ", max=" + max +
                '}';
    }
}
