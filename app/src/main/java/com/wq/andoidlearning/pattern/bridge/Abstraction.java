package com.wq.andoidlearning.pattern.bridge;

/**
 * 抽象部分
 */
public abstract class Abstraction {

    //抽象部分拥有实现部分的接口对象
    private Implementor implementor;

    /**
     * 设置接口对象
     *
     * @param implementor
     */
    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }

    //获取接口对象
    public Implementor getImplementor() {
        return implementor;
    }

    //调用接口对象的方法来完成操作
    protected void operation() {
        if (implementor != null) {
            implementor.operation();
        }
    }
}
