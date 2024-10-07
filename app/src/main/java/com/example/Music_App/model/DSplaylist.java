package com.example.Music_App.model;

import java.io.Serializable;

public class DSplaylist implements Serializable {

    private String MaDanhSachPlayList;

    private String Maloai;


    private String TenLoai;

    private String TenNhac;

    private String MaNhac;

    private String TenDSPlaylist;

    private String HINH;

    private String hinhNhac;


    public DSplaylist(String maDanhSachPlayList, String maloai, String tenLoai, String tenNhac, String maNhac, String tenDSPlaylist, String HINH, String hinhNhac) {
        MaDanhSachPlayList = maDanhSachPlayList;
        Maloai = maloai;
        TenLoai = tenLoai;
        TenNhac = tenNhac;
        MaNhac = maNhac;
        TenDSPlaylist = tenDSPlaylist;
        this.HINH = HINH;
        this.hinhNhac = hinhNhac;
    }

    public String getMaDanhSachPlayList() {
        return MaDanhSachPlayList;
    }

    public void setMaDanhSachPlayList(String maDanhSachPlayList) {
        MaDanhSachPlayList = maDanhSachPlayList;
    }

    public String getMaloai() {
        return Maloai;
    }

    public void setMaloai(String maloai) {
        Maloai = maloai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getTenNhac() {
        return TenNhac;
    }

    public void setTenNhac(String tenNhac) {
        TenNhac = tenNhac;
    }

    public String getMaNhac() {
        return MaNhac;
    }

    public void setMaNhac(String maNhac) {
        MaNhac = maNhac;
    }

    public String getTenDSPlaylist() {
        return TenDSPlaylist;
    }

    public void setTenDSPlaylist(String tenDSPlaylist) {
        TenDSPlaylist = tenDSPlaylist;
    }

    public String getHINH() {
        return HINH;
    }

    public void setHINH(String HINH) {
        this.HINH = HINH;
    }

    public String getHinhNhac() {
        return hinhNhac;
    }

    public void setHinhNhac(String hinhNhac) {
        this.hinhNhac = hinhNhac;
    }
}
