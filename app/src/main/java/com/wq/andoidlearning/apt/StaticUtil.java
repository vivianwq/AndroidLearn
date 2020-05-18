package com.wq.andoidlearning.apt;

import android.app.Activity;

import com.alpha.staticinject.StaticMapper;

public class StaticUtil {
    public static void inject(Activity activity) {
        StaticMapper.bind(activity);
    }

}
