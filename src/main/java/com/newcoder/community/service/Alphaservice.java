package com.newcoder.community.service;

import com.newcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//加了这个prototype就可以多次实例化初始化，否则一个bean中只会一次实例化初始化销毁，一般用不上这个Scope
//@Scope("prototype")
public class Alphaservice {

    @Autowired
    private AlphaDao alphaDao;

    public Alphaservice() {
        System.out.println("实例化Alphaservice");
    }
    @PostConstruct
    public void init() {
        System.out.println("初始化Alphaservice");
    }

    @PreDestroy
    public void destory() {
        System.out.println("销毁Alphaservice");
    }

    public String find() {
        return alphaDao.select();
    }
}
