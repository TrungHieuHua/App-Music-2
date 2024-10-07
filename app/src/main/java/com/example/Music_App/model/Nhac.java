package com.example.Music_App.model;


import java.io.Serializable;

public class Nhac implements Serializable {
    private String maNhac;
    private String hinhNhac;
    private String tenNhac;
    private String maLoai;
    private String maTacGia;
    private String maCaSi;
    private String maLoi;
    private String fileNhac;
    private String tenCaSi;

    public Nhac(String maNhac, String hinhNhac, String tenNhac, String maLoai, String maTacGia, String maCaSi, String maLoi, String fileNhac, String tenCaSi) {
        this.maNhac = maNhac;
        this.hinhNhac = hinhNhac;
        this.tenNhac = tenNhac;
        this.maLoai = maLoai;
        this.maTacGia = maTacGia;
        this.maCaSi = maCaSi;
        this.maLoi = maLoi;
        this.fileNhac = fileNhac;
        this.tenCaSi = tenCaSi;
    }

//    public Nhac(String maNhac, String hinhNhac, String tenNhac, String maLoai, String maTacGia, String maCaSi, String maLoi, String fileNhac, String tenCaSi) {
//        this.maNhac = maNhac;
//        this.hinhNhac = hinhNhac;
//        this.tenNhac = tenNhac;
//        this.maLoai = maLoai;
//        this.maTacGia = maTacGia;
//        this.maCaSi = maCaSi;
//        this.maLoi = maLoi;
//        this.fileNhac = fileNhac;
//        this.tenCaSi = tenCaSi;
//    }



    public String getMaNhac() {
        return maNhac;
    }

    public void setMaNhac(String maNhac) {
        this.maNhac = maNhac;
    }

    public String getHinhNhac() {
        return hinhNhac;
    }

    public void setHinhNhac(String hinhNhac) {
        this.hinhNhac = hinhNhac;
    }

    public String getTenNhac() {
        return tenNhac;
    }

    public void setTenNhac(String tenNhac) {
        this.tenNhac = tenNhac;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getMaCaSi() {
        return maCaSi;
    }

    public void setMaCaSi(String maCaSi) {
        this.maCaSi = maCaSi;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getFileNhac() {
        return fileNhac;
    }

    public void setFileNhac(String fileNhac) {
        this.fileNhac = fileNhac;
    }

    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }
}
