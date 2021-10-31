package com.eleven.icode.malluser.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author by YingLong on 2021/10/27
 */
@Configuration
@ConfigurationProperties(prefix = "eleven.task")
public class ThreadPoolConf {
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
}
