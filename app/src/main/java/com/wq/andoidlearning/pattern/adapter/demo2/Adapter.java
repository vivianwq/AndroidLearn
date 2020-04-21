package com.wq.andoidlearning.pattern.adapter.demo2;

/**
 * 类适配器模式,Adapter持有Src对象,并实现了Dst接口,当调用Dst接口声明
 * 的方法时,再调用内部持有的Src对象的方法,从而完成适配
 */
public class Adapter implements DstClass {
    private SrcClass srcClass;

    public Adapter(SrcClass srcClass) {
        this.srcClass = srcClass;
    }

    @Override
    public String dstMethod() {
        return srcClass.srcMethod();
    }
}
