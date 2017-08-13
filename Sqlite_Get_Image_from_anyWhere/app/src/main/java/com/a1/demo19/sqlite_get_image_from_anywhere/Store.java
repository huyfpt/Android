package com.a1.demo19.sqlite_get_image_from_anywhere;

/**
 * Created by duchuy on 12/5/2016.
 */
public class Store {
    int id;
    String name;
    float price;
    byte [] image;
    public Store(int id, String name, float price, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }


}
