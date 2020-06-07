package com.wq.andoidlearning.jvm;

import androidx.annotation.NonNull;

public class TestB implements Cloneable {
    public int a;
    public Student student;

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        TestB clone = (TestB) super.clone();
        clone.student = (Student) student.clone();
        Thread.currentThread().interrupt();
        boolean interrupted = Thread.currentThread().isInterrupted();
        return clone;

    }

    @Override
    public String toString() {
        return "TestB{" +
                "a=" + a +
                ", student=" + student +
                '}';
    }
}
