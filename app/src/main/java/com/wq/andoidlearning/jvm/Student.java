package com.wq.andoidlearning.jvm;

import androidx.annotation.NonNull;

public class Student implements Cloneable {
    public int age;


    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                '}';
    }
}
