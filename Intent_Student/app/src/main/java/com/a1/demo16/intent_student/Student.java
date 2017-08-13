package com.a1.demo16.intent_student;

import java.io.Serializable;

/**
 * Created by duchuy on 11/11/2016.
 */
public class Student implements Serializable{
    String name;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    int age;
}
