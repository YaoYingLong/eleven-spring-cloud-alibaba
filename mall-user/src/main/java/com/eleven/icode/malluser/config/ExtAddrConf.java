package com.eleven.icode.malluser.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author by YingLong on 2021/10/31
 */
@RefreshScope
@ConfigurationProperties(prefix = "test.ext")
public class ExtAddrConf implements InitializingBean {
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ext addr = " + addr);
    }
}
