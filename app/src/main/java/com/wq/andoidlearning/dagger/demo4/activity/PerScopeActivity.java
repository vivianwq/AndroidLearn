package com.wq.andoidlearning.dagger.demo4.activity;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface PerScopeActivity {
}
