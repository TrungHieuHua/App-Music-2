package com.example.Music_App.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.Music_App.database.Dbhelper;
import com.example.Music_App.model.DSplaylist;

import java.util.ArrayList;
import java.util.List;

public class DSplaylistDAO {

    private Dbhelper dbHelper;
    public DSplaylistDAO(Context context){
        dbHelper = new Dbhelper(context);
    }


//    public ArrayList<DSplaylist> getDSplaylist(){
//        ArrayList<DSplaylist> list = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DanhSachPlaylist", null);
//        if (cursor.getCount() > 0){
//            cursor.moveToFirst();
//            do{
//                list.add(new DSplaylist(cursor.getString(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getString(3),
//                        cursor.getString(4)));
//            }while (cursor.moveToNext());
//        }
//
//        return list;
//    }

    public List<DSplaylist> getTenloai(){
        List<DSplaylist> list = new ArrayList<>();
//        String theLoai = "?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT N.MaNhac, N.TenNhac, N.hinhNhac, N.MaLoai, TL.TenLoai\n" +
                "FROM Nhac N\n" +
                "JOIN TheLoai TL ON N.MaLoai = TL.MaLoai\n", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                String manhac = cursor.getString(0);
                String tenNhac = cursor.getString(1);
                String hinhnhac = cursor.getString(2);
                String maloai = cursor.getString(3);
                String tenloai = cursor.getString(4);

                DSplaylist dsplaylist = new DSplaylist("",maloai,tenloai,tenNhac,manhac,"","",hinhnhac);
                list.add(dsplaylist);
            }while (cursor.moveToNext());
        }cursor.close();

        return list;
}

    public List<DSplaylist> getss(String artistName) {
        List<DSplaylist> allSongs = getTenloai(); // Giả sử đây là phương thức trả về danh sách tất cả các bài hát
        List<DSplaylist> songsByArtist = new ArrayList<>();
        for (DSplaylist song : allSongs) {
            if (song.getTenLoai().equals(artistName)) {
                songsByArtist.add(song);
            }
        }
        return songsByArtist;
    }


}