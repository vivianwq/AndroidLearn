package com.wq.andoidlearning.pattern.bridge;

import android.util.Log;

public class ImplementorA implements Implementor {
    @Override
    public void operation() {
        Log.d("Implementor", "ImplementorA.operation");
    }
}
