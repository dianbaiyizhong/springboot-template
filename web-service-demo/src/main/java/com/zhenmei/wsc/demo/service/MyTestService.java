package com.zhenmei.wsc.demo.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class MyTestService implements IMyService{
    @Override
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public void test() {

    }
}
