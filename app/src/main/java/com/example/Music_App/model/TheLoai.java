package com.example.Music_App.model;

import java.io.Serializable;

public class TheLoai implements Serializable {

    private String MaLoai;

    private String TenLoai;

    private String MauTheLoai;

    public TheLoai(String maLoai, String tenLoai, String mauTheLoai) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
        MauTheLoai = mauTheLoai;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getMauTheLoai() {
        return MauTheLoai;
    }

    public void setMauTheLoai(String mauTheLoai) {
        MauTheLoai = mauTheLoai;
    }
}
