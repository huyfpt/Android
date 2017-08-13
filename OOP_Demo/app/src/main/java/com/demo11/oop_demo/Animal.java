package com.demo11.oop_demo;

import android.widget.Toast;

/**
 * Created by duchuy on 10/21/2016.
 */
public class Animal {
    private String name, color;
    private int foot;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFoot() {
        return foot;
    }

    public void setFoot(int foot) {
        this.foot = foot;
    }
//    public Animal(String name, String color, int foot) {
//        this.name = name;
//        this.color = color;
//        this.foot = foot;
//    }
//
//    public void SetFoot(int numFoot) {
//
//        if (numFoot < 2 || numFoot > 4) {
//            foot = 2;
//        } else {
//            foot = numFoot;
//
//        }
//    }
//
//    public int GetFoot() {
//        return foot;
//    }
}

