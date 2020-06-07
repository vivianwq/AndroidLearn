package com.wq.andoidlearning.jvm;

import androidx.annotation.NonNull;

public class TestA implements Cloneable{
    private int a;
    private String b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "TestA{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }
}
