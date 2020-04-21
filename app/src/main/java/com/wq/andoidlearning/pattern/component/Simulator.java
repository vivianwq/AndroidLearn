package com.wq.andoidlearning.pattern.component;

public class Simulator {
    public static void main(String[] args) {
        Component leaf = new Leaf("leaf1");
        Component leaf2 = new Leaf("leaf2");
        Component component = new Composite("composite1");
        component.addChild(leaf);
        component.addChild(leaf2);
        Component leaf3 = new Leaf("leaf3");
        Component leaf4 = new Leaf("leaf4");
        Component component2 = new Composite("composite2");
        component2.addChild(leaf3);
        component2.addChild(leaf4);
        component.doSomething();
        component2.doSomething();
    }
}
