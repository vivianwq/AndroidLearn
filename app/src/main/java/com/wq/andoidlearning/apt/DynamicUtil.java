package com.wq.andoidlearning.apt;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;
import java.lang.reflect.Field;

public class DynamicUtil {
    public static void inject(Activity activity) {
        Intent intent = activity.getIntent();
        //反射
        for (Field field : activity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(DynamicIntentKey.class)) {
                //获取注解
                DynamicIntentKey dynamicIntentKey = field.getAnnotation(DynamicIntentKey.class);
                String intentKey = dynamicIntentKey.value();

                //读取实际的IntentExtra值
                Serializable serializableExtra = intent.getSerializableExtra(intentKey);
                if (serializableExtra == null) {
                    if (field.getType().isAssignableFrom(String.class)) {
                        serializableExtra = intentKey;
                    }
                }
                try {
                    //插入值
                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);
                    field.set(activity, serializableExtra);
                    field.setAccessible(accessible);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
