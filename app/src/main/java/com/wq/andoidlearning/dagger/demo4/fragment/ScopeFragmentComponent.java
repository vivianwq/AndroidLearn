package com.wq.andoidlearning.dagger.demo4.fragment;

import dagger.Subcomponent;

@Subcomponent(modules = {ScopeFragmentModule.class})
@PerScopeFragment
public interface ScopeFragmentComponent {

    void inject(ScopeFragment scopeFragment);
}
