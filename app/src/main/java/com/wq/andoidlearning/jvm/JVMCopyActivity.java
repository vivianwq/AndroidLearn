package com.wq.andoidlearning.jvm;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class JVMCopyActivity extends AppCompatActivity {
    StringBuilder stringBuilder = new StringBuilder();

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_v_m_copy);
        tvContent = findViewById(R.id.tvContent);
        TestA testA = new TestA();
        testA.setA(100);
        testA.setB("Hello world");
        stringBuilder.append("原始数据" + testA + "\n");
        try {
            TestA clone = (TestA) testA.clone();
            clone.setB("改变一个数组");
            clone.setA(10000);
            stringBuilder.append("改变数据" + clone + "\n");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        stringBuilder.append("=============" + "\n");
        TestB testB = new TestB();
        Student student = new Student();
        student.age = 110;
        testB.student = student;
        testB.a = 1000;

        stringBuilder.append("TestB之前" + testB + "\n");

        try {
            TestB clone = (TestB) testB.clone();
            student.age = 999;
            stringBuilder.append("clone对象" + clone + "\n");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        tvContent.setText(stringBuilder);

    }
}
