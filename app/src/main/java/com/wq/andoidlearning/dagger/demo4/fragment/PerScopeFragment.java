package com.wq.andoidlearning.dagger.demo4.fragment;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

//每个Activity只有一个实例
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface PerScopeFragment {
}
