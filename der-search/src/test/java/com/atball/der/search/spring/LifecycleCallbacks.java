package com.atball.der.search.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class LifecycleCallbacks implements InitializingBean, DisposableBean {


    // @PreDestroy



    // 通过xml加入到bean定义中去
    // 或者通过加@PostConstruct注解
    @PostConstruct
    public void init(){

    }

    // 初始化之后回调
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    // Bean销毁的时候回调
    @Override
    public void destroy() throws Exception {

    }
}
