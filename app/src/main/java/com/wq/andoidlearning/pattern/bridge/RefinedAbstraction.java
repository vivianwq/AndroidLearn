package com.wq.andoidlearning.pattern.bridge;

public class RefinedAbstraction extends Abstraction {

    @Override
    protected void operation() {
        getImplementor().operation();
    }
}
