package com.wq.andoidlearning.pattern.adapter.demo1;

/**
 * 类适配器模式,继承于原始对象(SrcClass),并实现了客户端可以接受的接口DstClass
 */
public class Adapter extends SrcClass implements DstClass {
    @Override
    public String dstMethod() {
        return srcMethod();
    }
}
