package com.ardoirdtest1.android_sqlite_addition;

/**
 * Created by duchuy on 4/3/2017.
 */

public class Idiscovery {
    int id;
    String activity, location, date, time, name;
    byte[] image;
    public Idiscovery(Integer id, String activity, String location, String date, String time, String name, byte[] image) {
        this.id = id;
        this.activity = activity;
        this.location = location;
        this.date = date;
        this.time = time;
        this.name = name;
        this.image = image;
    }


}
