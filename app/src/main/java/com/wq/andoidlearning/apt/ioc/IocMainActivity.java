package com.wq.andoidlearning.apt.ioc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IocMainActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioc_main);
        tvContent = findViewById(R.id.tvContent);
        findViewById(R.id.btnIOC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioc();
            }
        });

        findViewById(R.id.btnDI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                di();
            }
        });


    }

    private void di() {
        //1.声明注册bean
        BeanDefined beanDefined = new BeanDefined();
        beanDefined.setBeanId("student");
        beanDefined.setClassPath("com.wq.andoidlearning.apt.ioc.Student");

        //设置property
        Map<String, String> propertyMap = beanDefined.getPropertyMap();
        propertyMap.put("name", "vivian");
        propertyMap.put("age", "22");
        propertyMap.put("teacher", "teacher");

        //注册教师类
        BeanDefined teacher = new BeanDefined();
        Map<String, String> propertyMap1 = teacher.getPropertyMap();
        propertyMap1.put("name","北京大学");
        teacher.setBeanId("teacher");
        teacher.setClassPath("com.wq.andoidlearning.apt.ioc.Teacher");

        List<BeanDefined> beanDefinedList = new ArrayList<>();
        beanDefinedList.add(beanDefined);
        beanDefinedList.add(teacher);

        //2.声明一个BeanFactory,类似于Spring中的ApplicationContext
        BeanFactory factory = new BeanFactory();
        factory.setBeanDefinedList(beanDefinedList);

        //3.开发人员向BeanFactory索要实例对象
        try {
            Student student = (Student) factory.getBean("student");
            tvContent.setText(student.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void ioc() {
        //1.声明注册bean
        BeanDefined beanDefined = new BeanDefined();
        beanDefined.setBeanId("student");
        beanDefined.setClassPath("com.wq.andoidlearning.apt.ioc.Student");
        List<BeanDefined> beanDefinedList = new ArrayList<>();
        beanDefinedList.add(beanDefined);

        //2.声明一个BeanFactory,类似于Spring中的ApplicationContext
        BeanFactory factory = new BeanFactory();
        factory.setBeanDefinedList(beanDefinedList);

        //3.开发人员向BeanFactory索要实例对象
        try {
            Student student = (Student) factory.getBean("student");
            tvContent.setText(student.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
