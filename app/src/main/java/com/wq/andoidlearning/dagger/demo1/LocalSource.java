package com.wq.andoidlearning.dagger.demo1;

import javax.inject.Inject;

public class LocalSource {

    @Inject
    public LocalSource() {

    }

    public String getData() {
        return "使用在构造函数上使用@Inject的方式,获取到了本地数据";
    }
}
