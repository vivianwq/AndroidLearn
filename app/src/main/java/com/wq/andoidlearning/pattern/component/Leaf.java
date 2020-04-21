package com.wq.andoidlearning.pattern.component;

import android.util.Log;

/**
 * 叶子对象
 */
public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void doSomething() {
        Log.d("Leaf", name + "- doSomething");

    }

    @Override
    public void addChild(Component component) {

    }

    @Override
    public void removeChild(Component component) {

    }
}
