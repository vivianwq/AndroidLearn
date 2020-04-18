package com.wq.andoidlearning.dagger.demo3;

import dagger.Component;

@Component(modules = DependencyModule.class)
public interface DependencyComponent {

    DependencySource getDependencySource();
}
