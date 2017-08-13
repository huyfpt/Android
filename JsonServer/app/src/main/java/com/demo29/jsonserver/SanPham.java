package com.demo29.jsonserver;

import java.io.Serializable;

/**
 * Created by duchuy on 1/10/2017.
 */
public class SanPham implements Serializable {
    public Integer ID;
    public String Ten;
    public Integer Gia;
    public String MoTa;

    public SanPham(Integer ID, String ten, Integer gia, String moTa) {
        this.ID = ID;
        Ten = ten;
        Gia = gia;
        MoTa = moTa;
    }
}
