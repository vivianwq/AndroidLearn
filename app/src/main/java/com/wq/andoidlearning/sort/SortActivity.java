package com.wq.andoidlearning.sort;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortActivity extends AppCompatActivity {

    class Father<T> {

        public Father() {
            Type genericSuperclass = getClass().getGenericSuperclass();
            Type actualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            stringBuilder.append("Father通过new子类获取的泛型类型---" + actualTypeArgument + "\n");

        }

        public void show(){

        }

    }

    class Son extends Father<Integer> {
        public Son() {
            Type genericSuperclass = getClass().getGenericSuperclass();
            Type actualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            stringBuilder.append("Son通过new子类获取的泛型类型---" + actualTypeArgument + "\n");

        }
    }


    private TextView tvContent;
    private int[] sortData = new int[]{6, 1, 2, 5, 7, 9, 3, 10};
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        tvContent = findViewById(R.id.tvContent);
        Integer a = 3;
        Boolean b = true;
        List<Integer> arrayList = new ArrayList<>();
        List<Boolean> booleanList = new ArrayList<>();
        stringBuilder.append(a.getClass() + "是否相等" +
                b.getClass() + "\n");
        stringBuilder.append(arrayList.getClass() + "是否相等" +
                booleanList.getClass() + "\n");
        stringBuilder.append("通过实例化子类--第一种方式" + "\n");
        Son integerSon = new Son();
        stringBuilder.append("通过匿名内部类--第二种方式" + "\n");
        Father<Boolean> father = new Father<Boolean>() {
            @Override
            public void show() {
                super.show();
            }
        };


        tvContent.setText(stringBuilder);
        findViewById(R.id.btnSort1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastSort.sort1(sortData);
                stringBuilder.append("冒泡:" + Arrays.toString(sortData) + "\n");
                tvContent.setText(stringBuilder);
            }
        });
    }
}
