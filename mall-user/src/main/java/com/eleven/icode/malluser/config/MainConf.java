package com.eleven.icode.malluser.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author by YingLong on 2021/10/31
 */
//@ConfigurationProperties(prefix = "test.main")
public class MainConf implements InitializingBean {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "MainConf{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
