package com.wq.andoidlearning.apt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.apt.ioc.IocMainActivity;
import com.wq.apt.BindView;
import com.wq.apt.StaticIntentKey;
import com.wq.inject_api.ButterKnife;

import java.lang.reflect.Method;

public class AptMainActivity extends AppCompatActivity {

    @BindView(R.id.tvContent)
    TextView tvContent;

    private StringBuilder stringBuilder;

    @DynamicIntentKey("key_name")
    private String dynamicName;

    @StaticIntentKey("static_data")
    String staticName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apt_main);
        ButterKnife.bind(this);
        tvContent.setText("通过apt注解查找控件并改变内容");
        stringBuilder = new StringBuilder();
        getIntent().putExtra("static_data", "静态注入");
        getIntent().putExtra("key_name", "动态注入");


        findViewById(R.id.btnByReflect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //使用类加载器加载类
                    Class c = Class.forName("com.wq.andoidlearning.apt.Child");
                    //找到类上面的注解
                    boolean isExist = c.isAnnotationPresent(Description.class);
                    //上面的这个方法是用这个类来判断这个类是否存在Description这样的一个注解
                    if (isExist) {
                        //拿到注解实例:解析类上面的注解
                        Description annotation = (Description) c.getAnnotation(Description.class);
                        System.out.println(annotation.value());
                        tvContent.setText(annotation.value());
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btnByReflectMethod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //使用类加载器加载类
                    Class c = Class.forName("com.wq.andoidlearning.apt.Child");
                    //找到类上面的注解
                    boolean isExist = c.isAnnotationPresent(Description.class);
                    //上面的这个方法是用这个类来判断这个类是否存在Description这样的一个注解
                    if (isExist) {
                        //拿到注解实例:解析类上面的注解
                        Description annotation = (Description) c.getAnnotation(Description.class);
                        System.out.println(annotation.value());
                        stringBuilder.append(annotation.value() + "\n");

                        //获取所有的方法
                    }

                    Method[] methods = c.getMethods();
                    for (Method m : methods) {
                        boolean isExist1 = m.isAnnotationPresent(Description.class);
                        if (isExist1) {
                            Description annotation = m.getAnnotation(Description.class);
                            System.out.println(annotation.value());
                            stringBuilder.append(annotation.value() + "\n");
                        }
                    }
                    tvContent.setText(stringBuilder.toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btnInjectByAn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DynamicUtil.inject(AptMainActivity.this);
                tvContent.setText(dynamicName);

            }
        });
        findViewById(R.id.btnInjectStatic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticUtil.inject(AptMainActivity.this);
                tvContent.setText(staticName);
            }
        });

        findViewById(R.id.btn1000).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //静态注入
                long start = System.currentTimeMillis();
                for (int index = 0; index < 1000; index++) {
                    StaticUtil.inject(AptMainActivity.this);
                }
                long duration = System.currentTimeMillis() - start;
                Log.i("onCreate", "StaticIntentKey==name =" + staticName+"耗时"+duration);
                stringBuilder.append("StaticIntentKey==name =" + staticName+"耗时"+duration+"\n");
                //动态注入--反射
                start = System.currentTimeMillis();
                for (int index = 0; index < 1000; index++) {
                    DynamicUtil.inject(AptMainActivity.this);
                }
                duration = System.currentTimeMillis() - start;
                Log.i("onCreate", "DynamicIntentKey==name =" + dynamicName+"耗时"+duration);
                stringBuilder.append("DynamicIntentKey==name =" + dynamicName+"耗时"+duration+"\n");
                tvContent.setText(stringBuilder);
            }
        });


        findViewById(R.id.btnIOC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AptMainActivity.this, IocMainActivity.class);
                startActivity(intent);
            }
        });
    }

}
