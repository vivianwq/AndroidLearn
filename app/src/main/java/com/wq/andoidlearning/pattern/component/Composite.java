package com.wq.andoidlearning.pattern.component;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {

    //叶子对象的集合
    private List<Component> components;

    public Composite(String name) {
        super(name);
        components = new ArrayList<>();
    }

    @Override
    public void doSomething() {
        for (Component component : components) {
            component.doSomething();
        }
    }

    @Override
    public void addChild(Component component) {
        components.add(component);
    }

    @Override
    public void removeChild(Component component) {
        components.remove(component);
    }
}
