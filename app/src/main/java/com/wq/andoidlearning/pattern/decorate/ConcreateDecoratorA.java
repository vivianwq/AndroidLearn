package com.wq.andoidlearning.pattern.decorate;

public class ConcreateDecoratorA extends Decorator {
    public ConcreateDecoratorA(Component component) {
        super(component);
    }

    @Override
    void operate() {
        //在父类之前调用装饰方法
        operateBefore();
        super.operate();
        //在父类之后调用装饰方法
        operateAfter();
    }

    //装饰方法A
    private void operateBefore() {

    }

    //装饰方法B
    private void operateAfter() {

    }
}
