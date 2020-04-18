package com.wq.andoidlearning.dagger.demo3;

import dagger.Subcomponent;

@Subcomponent
public interface SubSourceComponent {

    void inject(SubRepository subRepository);
}

