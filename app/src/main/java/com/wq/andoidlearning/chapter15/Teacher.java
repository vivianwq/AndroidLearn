package com.wq.andoidlearning.chapter15;

import java.io.Serializable;

public class Teacher implements Serializable {
    private int age;
    private String name;

    public Teacher() {
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
