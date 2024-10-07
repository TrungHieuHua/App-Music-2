
package com.example.Music_App.model;

import java.io.Serializable;

public class Casi implements Serializable {

    private String MaCaSi;

    private String TenCaSi;

    private String QueQuan;

    private String NamSinh;

    private String HinhCaSi;

    private String Hinhalbum;

    public Casi(String maCaSi, String tenCaSi, String queQuan, String namSinh, String hinhCaSi, String hinhalbum) {
        MaCaSi = maCaSi;
        TenCaSi = tenCaSi;
        QueQuan = queQuan;
        NamSinh = namSinh;
        HinhCaSi = hinhCaSi;
        Hinhalbum = hinhalbum;
    }

    public String getMaCaSi() {
        return MaCaSi;
    }

    public void setMaCaSi(String maCaSi) {
        MaCaSi = maCaSi;
    }

    public String getTenCaSi() {
        return TenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        TenCaSi = tenCaSi;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }

    public String getHinhCaSi() {
        return HinhCaSi;
    }

    public void setHinhCaSi(String hinhCaSi) {
        HinhCaSi = hinhCaSi;
    }

    public String getHinhalbum() {
        return Hinhalbum;
    }

    public void setHinhalbum(String hinhalbum) {
        Hinhalbum = hinhalbum;
    }
}
