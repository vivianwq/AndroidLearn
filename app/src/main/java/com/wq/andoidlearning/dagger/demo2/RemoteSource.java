package com.wq.andoidlearning.dagger.demo2;

public class RemoteSource implements Source {
    @Override
    public String getData() {
        return "读取网络数据成功";
    }
}
