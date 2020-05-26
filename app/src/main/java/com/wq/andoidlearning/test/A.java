package com.wq.andoidlearning.test;

public class A {
    //双重检测
    private static volatile A a;
    private static boolean isInit = false;

    private A() {
        if (!isInit) {
            synchronized (A.class) {
                isInit = true;
            }
        } else {
            throw new RuntimeException("已经初始化了");
        }

    }

    public static A getInstance() {
        if (a == null) {
            synchronized (A.class) {
                if (a == null)
                    a = new A();
            }
        }
        return a;
    }

}
