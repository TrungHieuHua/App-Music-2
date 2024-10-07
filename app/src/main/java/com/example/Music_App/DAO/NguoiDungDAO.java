package com.example.Music_App.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.Music_App.database.Dbhelper;

public class NguoiDungDAO {
    private Dbhelper dbHelper; // Sửa từ dbhelper thành dbHelper
    private SharedPreferences sharedPreferences;

    public NguoiDungDAO(Context context) {
        dbHelper = new Dbhelper(context);
        sharedPreferences = context.getSharedPreferences("dataUser", Context.MODE_PRIVATE);
    }

    // Đăng ký
    public int Dangky(String Gmail, String MatKhaudk, String NamSinh, String GioiTinh, String TenUserdk) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase(); // Sửa từ dbhelper thành dbHelper
        // Kiểm tra
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE Gmail = ? OR TenUser = ?", new String[]{Gmail, TenUserdk});
        if (cursor.getCount() > 0) {
            cursor.close(); // Đảm bảo đóng cursor
            return 0; // Email hoặc tên người dùng đã tồn tại
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("Gmail", Gmail);
            contentValues.put("MatKhau", MatKhaudk);
            contentValues.put("NamSinh", NamSinh);
            contentValues.put("GioiTinh", GioiTinh);
            contentValues.put("TenUser", TenUserdk);
            long check = sqLiteDatabase.insert("User", null, contentValues);
            cursor.close(); // Đảm bảo đóng cursor
            return (check == -1) ? -1 : 1; // Trả về kết quả chèn
        }
    }

    // Kiểm tra thông tin đăng nhập
    public boolean KiemTraDangNhap(String TenUserOrGmail, String MatKhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase(); // Sửa từ dbhelper thành dbHelper
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM User WHERE (TenUser = ? OR Gmail = ?) AND MatKhau = ?",
                new String[]{TenUserOrGmail, TenUserOrGmail, MatKhau}
        );

        boolean result = false;

        if (cursor != null && cursor.moveToFirst()) {
            // Lưu role acc
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("role", cursor.getInt(7)); // Giả sử cột thứ 7 là role
            editor.putString("user_id", cursor.getString(0)); // Lưu user_id nếu cần
            editor.apply();
            result = true;
        }

        if (cursor != null) {
            cursor.close(); // Đảm bảo đóng cursor
        }

        return result; // Trả về kết quả đăng nhập
    }


    public String getUserId(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT MaUser FROM User WHERE Gmail = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        String userId = null;
        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getString(0); // Lấy MaUser
        }
        if (cursor != null) {
            cursor.close();
        }
        return userId;
    }
}
