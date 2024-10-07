package com.example.Music_App.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.Music_App.database.Dbhelper;
import com.example.Music_App.model.TheLoai;

import java.util.ArrayList;

public class TheLoaiDAO {

    private Dbhelper dbHelper;

    public TheLoaiDAO(Context context) {
        dbHelper = new Dbhelper(context);
    }

    public ArrayList<TheLoai> getTheLoai() {
        ArrayList<TheLoai> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TheLoai", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new TheLoai(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    // Phương thức thêm dữ liệu vào bảng TheLoai
    public boolean themTheLoai(TheLoai theLoai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaLoai", theLoai.getMaLoai());
        values.put("TenLoai", theLoai.getTenLoai());
        values.put("MauTheLoai", theLoai.getMauTheLoai());

        long result = db.insert("TheLoai", null, values);
        return result != -1;
    }

    // Phương thức cập nhật dữ liệu trong bảng TheLoai
    public boolean suaTheLoai(TheLoai theLoai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai", theLoai.getTenLoai());
        values.put("MauTheLoai", theLoai.getMauTheLoai());

        int result = db.update("TheLoai", values, "MaLoai = ?", new String[]{String.valueOf(theLoai.getMaLoai())});
        db.close();
        return result > 0;
    }

    // Phương thức xóa dữ liệu trong bảng TheLoai
    public boolean xoaTheLoai(String maLoai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete("TheLoai", "MaLoai = ?", new String[]{String.valueOf(maLoai)});
        db.close();
        return result > 0;
    }





}
