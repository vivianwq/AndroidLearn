package com.wq.andoidlearning.pattern.bridge;

import android.util.Log;

public class ImplementorB implements Implementor {
    @Override
    public void operation() {
        Log.d("Implementor", "ImplementorB.operation");
    }
}
