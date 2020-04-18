package com.wq.andoidlearning.dagger.demo3;

import javax.inject.Inject;

public class DataRepository {


    @Inject
    DependencySource dependencySource;

    public DataRepository() {
        //1.实例化所依赖的 Component
        DependencyComponent dependencyComponent = DaggerDependencyComponent.create();
        //2.在构建时传入依赖的Component实例
        DaggerSourceComponent.builder().dependencyComponent(dependencyComponent).build().inject(this);
    }

    public String getDependencyData() {
        return dependencySource.getData();
    }
}
