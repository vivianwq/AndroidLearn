package com.wq.andoidlearning.pattern.component;

/**
 * 基类
 */
public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    //定义抽象方法
    public abstract void doSomething();

    //添加子节点
    public abstract void addChild(Component component);

    //移除子节点
    public abstract void removeChild(Component component);
}
