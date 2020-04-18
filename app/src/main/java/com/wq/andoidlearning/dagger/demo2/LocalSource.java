package com.wq.andoidlearning.dagger.demo2;

public class LocalSource implements Source {
    @Override
    public String getData() {
        return "读取本地数据成功";
    }
}
