package com.wq.andoidlearning.pattern.adapter.demo3;

public class Client {
    private IDst iDst;

    void addUpdateListener(IDst dst) {
        this.iDst = dst;
    }

    void call() {
        iDst.dstMethod1();
        iDst.dstMethod2();
        iDst.dstMethod3();
    }
}
