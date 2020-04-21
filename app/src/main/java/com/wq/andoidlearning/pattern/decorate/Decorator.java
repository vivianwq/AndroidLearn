package com.wq.andoidlearning.pattern.decorate;

/**
 * 抽象装饰者
 */
public class Decorator extends Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    void operate() {
        component.operate();
    }
}
