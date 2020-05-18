package com.wq.andoidlearning.apt;

@Description("I am class annotation")
public class Child implements People {
    @Description("I am method annotation")
    @Override
    public String name() {
        return null;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    public void work() {

    }
}
