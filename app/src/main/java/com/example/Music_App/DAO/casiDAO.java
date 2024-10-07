package com.example.Music_App.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.Music_App.database.Dbhelper;
import com.example.Music_App.model.Casi;

import java.util.ArrayList;

public class casiDAO {

    private Dbhelper dbHelper;

    public casiDAO(Context context) {
        dbHelper = new Dbhelper(context);
    }



    public ArrayList<Casi> getcasi(){
        ArrayList<Casi> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CaSi", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                list.add(new Casi(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
    public Casi getArtistById(String id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CaSi WHERE MaCaSi = ?", new String[]{id});
        if (cursor != null && cursor.moveToFirst()) {
            Casi artist = new Casi(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
            cursor.close();
            return artist;
        }
        return null;
    }


    public long themCasi(Casi casi) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Hinhalbum", casi.getHinhalbum());
        contentValues.put("MaCaSi", casi.getMaCaSi());
        contentValues.put("TenCaSi", casi.getTenCaSi());
        contentValues.put("QueQuan", casi.getQueQuan());
        contentValues.put("NamSinh", casi.getNamSinh());
        contentValues.put("HinhCaSi", casi.getHinhCaSi());
        long result = sqLiteDatabase.insert("CaSi", null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public int capNhatCasi(Casi casi) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Hinhalbum", casi.getHinhalbum());
        contentValues.put("MaCaSi", casi.getMaCaSi());
        contentValues.put("TenCaSi", casi.getTenCaSi());
        contentValues.put("QueQuan", casi.getQueQuan());
        contentValues.put("NamSinh", casi.getNamSinh());
        contentValues.put("HinhCaSi", casi.getHinhCaSi());

        return sqLiteDatabase.update("CaSi", contentValues, "MaCaSi = ?", new String[]{casi.getMaCaSi()});
    }
    public boolean xoaCasi(String maCaSi) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int result = sqLiteDatabase.delete("CaSi", "MaCaSi = ?", new String[]{maCaSi});
        sqLiteDatabase.close();
        return result > 0;
    }
}



