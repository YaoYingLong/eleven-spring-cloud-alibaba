package com.eleven.icode.malluser.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author by YingLong on 2021/10/31
 */
@ConfigurationProperties(prefix = "test.shared")
public class ShareAddrConf implements InitializingBean {
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("share addr = " + addr);
    }
}
