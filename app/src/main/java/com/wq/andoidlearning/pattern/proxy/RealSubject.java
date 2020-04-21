package com.wq.andoidlearning.pattern.proxy;

import android.util.Log;

/**
 * 实现抽象主题的真实主题类
 */
public class RealSubject extends Subject {
    @Override
    void visit() {
        Log.d("RealSubject", "visit()");
    }
}
