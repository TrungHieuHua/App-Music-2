package com.example.Music_App.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Dbhelper extends SQLiteOpenHelper {

    public Dbhelper(Context context){

        super(context,"music",null,28);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TheLoai
        String QuanLyTheLoai = "CREATE TABLE TheLoai(MaLoai TEXT PRIMARY KEY, TenLoai TEXT, MauTheLoai TEXT)";
        sqLiteDatabase.execSQL(QuanLyTheLoai);

        // TacGia
        String QuanLyTacGia = "CREATE TABLE TacGia(MaTacGia TEXT PRIMARY KEY, TenTacGia TEXT, NamSinh TEXT, QueQuan TEXT)";
        sqLiteDatabase.execSQL(QuanLyTacGia);

        // CaSi
        String QuanLyCaSi = "CREATE TABLE CaSi(MaCaSi TEXT PRIMARY KEY, TenCaSi TEXT, QueQuan TEXT, NamSinh TEXT, HinhCaSi TEXT, Hinhalbum TEXT)";
        sqLiteDatabase.execSQL(QuanLyCaSi);

        // LoiNhac
        String QuanLyLoiNhac = "CREATE TABLE LoiNhac(MaLoi TEXT PRIMARY KEY, TenLoi TEXT)";
        sqLiteDatabase.execSQL(QuanLyLoiNhac);

        // Nhac
        String QuanLyNhac = "CREATE TABLE Nhac(MaNhac TEXT PRIMARY KEY,HinhNhac TEXT, TenNhac TEXT, MaLoai TEXT, MaTacGia TEXT, MaCaSi TEXT, MaLoi TEXT, FileNhac TEXT," +
                "FOREIGN KEY(MaLoai) REFERENCES TheLoai(MaLoai)," +
                "FOREIGN KEY(MaTacGia) REFERENCES TacGia(MaTacGia)," +
                "FOREIGN KEY(MaCaSi) REFERENCES CaSi(MaCaSi)," +
                "FOREIGN KEY(MaLoi) REFERENCES LoiNhac(MaLoi))";
        sqLiteDatabase.execSQL(QuanLyNhac);

        // User
        String QuanLyUser = "CREATE TABLE User(MaUser TEXT PRIMARY KEY, TenUser TEXT, Gmail TEXT, MatKhau TEXT, GioiTinh TEXT, NamSinh TEXT, DiaChi TEXT, role INTEGER)";
        sqLiteDatabase.execSQL(QuanLyUser);

        // PlayList
        String QuanLyPlayList = "CREATE TABLE PlayList(MaPlayList TEXT PRIMARY KEY, MaUser TEXT," +
                "FOREIGN KEY(MaUser) REFERENCES User(MaUser))";
        sqLiteDatabase.execSQL(QuanLyPlayList);

        // DanhSachPlayList
        String QuanLyDanhSachPlaylist = "CREATE TABLE DanhSachPlaylist(MaDanhSachPlayList TEXT PRIMARY KEY, MaNhac TEXT,TenDSPlaylist TEXT,HINH TEXT," +
                "FOREIGN KEY(MaNhac) REFERENCES Nhac(MaNhac))";
        sqLiteDatabase.execSQL(QuanLyDanhSachPlaylist);

        String YeuThich = "CREATE TABLE YeuThich(MaUser TEXT, MaNhac TEXT)";
        sqLiteDatabase.execSQL(YeuThich);
        // Insert sample data
        insertSampleData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS User");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DanhSachPlaylist");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PlayList");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Nhac");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TheLoai");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TacGia");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CaSi");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LoiNhac");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS YeuThich");

            onCreate(sqLiteDatabase);
        }
    }

    private void insertSampleData(SQLiteDatabase sqLiteDatabase) {
        // Insert sample data into TheLoai
        String insertTheLoai1 = "INSERT INTO TheLoai (MaLoai, TenLoai, MauTheLoai) " +
                "VALUES ('L1', 'Pop', 'Red')";
        String insertTheLoai2 = "INSERT INTO TheLoai (MaLoai, TenLoai, MauTheLoai) " +
                "VALUES ('L2', 'Rock', 'Blue')";
        String insertTheLoai3 = "INSERT INTO TheLoai (MaLoai, TenLoai, MauTheLoai) " +
                "VALUES ('L3', 'Disco', 'Blue')";
        String insertTheLoai4 = "INSERT INTO TheLoai (MaLoai, TenLoai, MauTheLoai) " +
                "VALUES ('L4', 'R&B', 'Blue')";
        String insertTheLoai5 = "INSERT INTO TheLoai (MaLoai, TenLoai, MauTheLoai) " +
                "VALUES ('L5', 'EDM', 'Blue')";
        String insertTheLoai6 = "INSERT INTO TheLoai (MaLoai, TenLoai, MauTheLoai) " +
                "VALUES ('L6', 'Trap', 'Blue')";
        String insertTheLoai7 = "INSERT INTO TheLoai (MaLoai, TenLoai, MauTheLoai) " +
                "VALUES ('L7', 'Neo-soul', 'Blue')";
        String insertTheLoai8 = "INSERT INTO TheLoai (MaLoai, TenLoai, MauTheLoai) " +
                "VALUES ('L8', 'Ballad', 'Blue')";

        sqLiteDatabase.execSQL(insertTheLoai1);
        sqLiteDatabase.execSQL(insertTheLoai2);
        sqLiteDatabase.execSQL(insertTheLoai3);
        sqLiteDatabase.execSQL(insertTheLoai4);
        sqLiteDatabase.execSQL(insertTheLoai5);
        sqLiteDatabase.execSQL(insertTheLoai6);
        sqLiteDatabase.execSQL(insertTheLoai7);
        sqLiteDatabase.execSQL(insertTheLoai8);

        // Insert sample data into TacGia
        String insertTacGia1 = "INSERT INTO TacGia (MaTacGia, TenTacGia, NamSinh, QueQuan) " +
                "VALUES ('TG1', 'Nguyen Nhat Anh', '1955', 'Quang Nam')";
        String insertTacGia2 = "INSERT INTO TacGia (MaTacGia, TenTacGia, NamSinh, QueQuan) " +
                "VALUES ('TG2', 'To Huu', '1920', 'Thua Thien Hue')";
        String insertTacGia3 = "INSERT INTO TacGia (MaTacGia, TenTacGia, NamSinh, QueQuan) " +
                "VALUES ('TG3', 'Taylor Swift', '1989', 'USA')";
        sqLiteDatabase.execSQL(insertTacGia1);
        sqLiteDatabase.execSQL(insertTacGia2);
        sqLiteDatabase.execSQL(insertTacGia3);

        // Insert sample data into CaSi
        String insertCaSi1 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS1', 'Black Pink', 'Hàn Quốc', '2016', 'blackpink','bp_album')";
        String insertCaSi2 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS2', 'Jennie', 'Hàn Quốc', '1996', 'jenni','jennialbum')";
        String insertCaSi3 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS3', 'Jiso', 'Hàn Quốc', '1995', 'jiso','jisoalbum')";
        String insertCaSi4 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS4', 'Jungkook', 'Hàn Quốc', '1995', 'jungkook','vvalbum')";
        String insertCaSi5 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS5', 'Lisa', 'Thái Lan', '1997', 'lisa','lisaalbum')";
        String insertCaSi6 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS6', 'Charlie Puth', 'USA', '1995', 'charlie','charlie_bg')";
        String insertCaSi7 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS7', 'Rosé', 'Úc', '1997', 'rose','rosealbum')";
        String insertCaSi8 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS8', 'V-BTS', 'Hàn Quốc', '1994', 'v','valbum')";
        String insertCaSi9 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS9', 'Taylor Swift', 'USA', '1989', 'taylor','stayloralbum')";
        String insertCaSi10 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS10', 'Trúc Nhân', 'Hồ Chí Minh', '1989', 'truc_nhan','truc_nhan_album')";
        String insertCaSi11 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS11', 'Bùi Anh Tuấn', 'Hà Nội', '1990', 'bui_anh_tuan','bui_anh_tuan_album')";
        String insertCaSi12 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS12', 'Vũ Cát Tường ', 'Hồ Chí Minh', '1990', 'vu_cat_tuong','vu_cat_tuong_album')";
        String insertCaSi13 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS13', 'Jaykii ', 'Hồ Chí Minh', '1992', 'jaykii','jaykii_album')";
        String insertCaSi14 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS14', 'Sơn Tùng ', 'Thái Bình', '1994', 'son_tung','son_tung_album')";
        String insertCaSi15 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS15', 'Mr.Siro ', 'Hà Nội', '1986', 'mr_siro','mr_siro_album')";
        String insertCaSi16 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS16', 'Đức Phúc', 'Hà Nội', '1996', 'duc_phuc','duc_phuc_album')";
        String insertCaSi17 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS17', 'ERIK', 'Hà Nội', '1997', 'erik','erik_album')";
        String insertCaSi18 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS18', 'Lou Hoàng', 'Hồ Chí Minh', '1994', 'lou_hoang','lou_hoang_album')";
        String insertCaSi19 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS19', 'BTS', 'Korea', '2013', 'bts','bts_album')";
        String insertCaSi20 = "INSERT INTO CaSi (MaCaSi, TenCaSi, QueQuan, NamSinh, HinhCaSi,Hinhalbum) " +
                "VALUES ('CS20', 'Justin Bieber', 'London', '1994', 'justin_bieber','justin_bieber_album')";
        sqLiteDatabase.execSQL(insertCaSi1);
        sqLiteDatabase.execSQL(insertCaSi2);
        sqLiteDatabase.execSQL(insertCaSi3);
        sqLiteDatabase.execSQL(insertCaSi4);
        sqLiteDatabase.execSQL(insertCaSi5);
        sqLiteDatabase.execSQL(insertCaSi6);
        sqLiteDatabase.execSQL(insertCaSi7);
        sqLiteDatabase.execSQL(insertCaSi8);
        sqLiteDatabase.execSQL(insertCaSi9);
        sqLiteDatabase.execSQL(insertCaSi10);
        sqLiteDatabase.execSQL(insertCaSi11);
        sqLiteDatabase.execSQL(insertCaSi12);
        sqLiteDatabase.execSQL(insertCaSi13);
        sqLiteDatabase.execSQL(insertCaSi14);
        sqLiteDatabase.execSQL(insertCaSi15);
        sqLiteDatabase.execSQL(insertCaSi16);
        sqLiteDatabase.execSQL(insertCaSi17);
        sqLiteDatabase.execSQL(insertCaSi18);
        sqLiteDatabase.execSQL(insertCaSi19);
        sqLiteDatabase.execSQL(insertCaSi20);

        // Insert sample data into LoiNhac
        String insertLoiNhac1 = "INSERT INTO LoiNhac (MaLoi, TenLoi) " +
                "VALUES ('Loi1', 'Loi nhac 1')";
        String insertLoiNhac2 = "INSERT INTO LoiNhac (MaLoi, TenLoi) " +
                "VALUES ('Loi2', 'Loi nhac 2')";
        sqLiteDatabase.execSQL(insertLoiNhac1);
        sqLiteDatabase.execSQL(insertLoiNhac2);

        // Insert sample data into Nhac
        String insertNhac1 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N1','killthislove' ,'Kill This Love', 'L1', 'TG1', 'CS1', 'Loi1', 'kill_this_love')";
        String insertNhac2 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N2', 'stay_backpink' , 'Stay', 'L2', 'TG2', 'CS1', 'Loi2', 'stay')";
        String insertNhac3 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N3', 'howyoulikethat' , 'How You Like That', 'L3', 'TG3', 'CS1', 'Loi3', 'how_you_like_that')";
        String insertNhac4 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N4', 'lovesick' , 'Lovesick Girl', 'L4', 'TG4', 'CS1', 'Loi4', 'love_sick_girl')";
        String insertNhac5 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N5', 'stayloralbum' , 'Love Story', 'L5', 'TG5', 'CS9', 'Loi5', 'love_story')";
        String insertNhac6 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N6', 'snake_it_off2' , 'Shake it off', 'L1', 'TG5', 'CS9', 'Loi5', 'shake_it_off')";
        String insertNhac7 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N7', 'blank_space' , 'Blank Space', 'L1', 'TG5', 'CS9', 'Loi5', 'blank_space')";
        String insertNhac8 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N8', 'you_belong_with_me2' , 'You Belong With Me', 'L1', 'TG5', 'CS9', 'Loi5', 'you_belong_with_me')";
        String insertNhac9 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N9', 'still_with' , 'Still With You', 'L4', 'TG5', 'CS4', 'Loi9', 'still_with_you')";
        String insertNhac10 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N10', 'my_time' , 'My Time', 'L4', 'TG5', 'CS4', 'Loi9', 'my_time')";
        String insertNhac11 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N11', 'euphoria' , 'Euphoria', 'L1', 'TG5', 'CS4', 'Loi9', 'euphoria')";
        String insertNhac12 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N12', 'solo' , 'Solo', 'L1', 'TG5', 'CS2', 'Loi9', 'solo')";
        String insertNhac13 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N13', 'flower' , 'Flower', 'L1', 'TG5', 'CS3', 'Loi9', 'flower')";
        String insertNhac14 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N14', 'lalisa' , 'Lalisa', 'L1', 'TG5', 'CS5', 'Loi9', 'lalisa')";
        String insertNhac15 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N15', 'money' , 'Money', 'L6', 'TG5', 'CS5', 'Loi9', 'money')";
        String insertNhac16 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N16', 'rockstar2' , 'RockStar', 'L2', 'TG5', 'CS5', 'Loi9', 'rockstar')";
        String insertNhac17 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N17', 'attention' , 'Attention', 'L4', 'TG5', 'CS6', 'Loi9', 'attention')";
        String insertNhac18 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N18', 'see_you_again' , 'See You Again', 'L2', 'TG5', 'CS6', 'Loi9', 'see_you_again')";
        String insertNhac19 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N19', 'we_do_not_talk_anymore' , 'We Dont Talk Anymore', 'L1', 'TG5', 'CS6', 'Loi9', 'we_do_not_talk_anymore')";
        String insertNhac20 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N20', 'one_call_away' , 'One Call Away', 'L1', 'TG5', 'CS6', 'Loi9', 'one_call_away')";
        String insertNhac21 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N21', 'how_long' , 'How Long', 'L1', 'TG5', 'CS6', 'Loi9', 'how_long')";
        String insertNhac22 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N22', 'gone2' , 'Gone', 'L1', 'TG5', 'CS7', 'Loi9', 'gone')";
        String insertNhac23 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N23', 'stigma' , 'Stigma', 'L4', 'TG5', 'CS8', 'Loi9', 'stigma')";
        String insertNhac24 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N24', 'singularity' , 'Singularity', 'L7', 'TG5', 'CS8', 'Loi9', 'singularity')";
        String insertNhac25 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N25', 'winter_bear' , 'Winter Bear', 'L1', 'TG5', 'CS8', 'Loi9', 'winter_bear')";
        String insertNhac26 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N26', 'scenery' , 'Scenery', 'L8', 'TG5', 'CS8', 'Loi9', 'scenery')";
        String insertNhac27 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N27', 'sweet_night' , 'Sweet Night', 'L1', 'TG5', 'CS8', 'Loi9', 'sweet_night')";
        String insertNhac28 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N28', 'nguoi_ta_co_thuong_minh_dau' , 'Người Ta Có Thương Mình Đau', 'L1', 'TG5', 'CS10', 'Loi9', 'nguoi_ta_co_thuong_minh_dau')";
        String insertNhac29 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N29', 'lon_roi_con_khoc_nhe' , 'Lớn Rồi Còn Khóc Nhè', 'L8', 'TG5', 'CS10', 'Loi9', 'lon_roi_con_khoc_nhe')";
        String insertNhac30 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N30', 'sang_mat_chua' , 'Sáng Mắt Chưa', 'L1', 'TG5', 'CS10', 'Loi9', 'sang_mat_chua')";
        String insertNhac31 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N31', 'that_bat_ngo' , 'Thật Bất Ngờ', 'L1', 'TG5', 'CS10', 'Loi9', 'that_bat_ngo')";
        String insertNhac32 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N32', 'bon_chu_lam' , 'Bốn Chữ Lắm', 'L1', 'TG5', 'CS10', 'Loi9', 'bon_chu_lam')";
        String insertNhac33 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N33', 'thuan_theo_y_troi' , 'Thuận Theo Ý Trời', 'L8', 'TG5', 'CS11', 'Loi9', 'thuan_theo_y_troi')";
        String insertNhac34 = "INSERT INTO Nhac (MaNhac, HinhNhac, TenNhac, MaLoai, MaTacGia, MaCaSi, MaLoi, FileNhac) " +
                "VALUES ('N34', 'noi_tinh_yeu_bat_dau' , 'Nơi Tình Yêu Bắt Đầu', 'L1', 'TG5', 'CS11', 'Loi9', 'noi_tinh_yeu_bat_dau')";
        sqLiteDatabase.execSQL(insertNhac1);
        sqLiteDatabase.execSQL(insertNhac2);
        sqLiteDatabase.execSQL(insertNhac3);
        sqLiteDatabase.execSQL(insertNhac4);
        sqLiteDatabase.execSQL(insertNhac5);
        sqLiteDatabase.execSQL(insertNhac6);
        sqLiteDatabase.execSQL(insertNhac7);
        sqLiteDatabase.execSQL(insertNhac8);
        sqLiteDatabase.execSQL(insertNhac9);
        sqLiteDatabase.execSQL(insertNhac10);
        sqLiteDatabase.execSQL(insertNhac11);
        sqLiteDatabase.execSQL(insertNhac12);
        sqLiteDatabase.execSQL(insertNhac13);
        sqLiteDatabase.execSQL(insertNhac14);
        sqLiteDatabase.execSQL(insertNhac15);
        sqLiteDatabase.execSQL(insertNhac16);
        sqLiteDatabase.execSQL(insertNhac17);
        sqLiteDatabase.execSQL(insertNhac18);
        sqLiteDatabase.execSQL(insertNhac19);
        sqLiteDatabase.execSQL(insertNhac20);
        sqLiteDatabase.execSQL(insertNhac21);
        sqLiteDatabase.execSQL(insertNhac22);
        sqLiteDatabase.execSQL(insertNhac23);
        sqLiteDatabase.execSQL(insertNhac24);
        sqLiteDatabase.execSQL(insertNhac25);
        sqLiteDatabase.execSQL(insertNhac26);
        sqLiteDatabase.execSQL(insertNhac27);
        sqLiteDatabase.execSQL(insertNhac28);
        sqLiteDatabase.execSQL(insertNhac29);
        sqLiteDatabase.execSQL(insertNhac30);
        sqLiteDatabase.execSQL(insertNhac31);
        sqLiteDatabase.execSQL(insertNhac32);
        sqLiteDatabase.execSQL(insertNhac33);
        //sqLiteDatabase.execSQL(insertNhac34);

        // Insert sample data into User
        String insertUser1 = "INSERT INTO User (MaUser, TenUser, Gmail, MatKhau, GioiTinh, NamSinh, DiaChi, role) " +
                "VALUES ('U1', 'Hieu', 'tannie1101999@gmail.com', '123456', 'Nữ', '1999', 'Hà Nội', 1)";
        String insertUser2 = "INSERT INTO User (MaUser, TenUser, Gmail, MatKhau, GioiTinh, NamSinh, DiaChi, role) " +
                "VALUES ('U2', 'admin', 'admin@gmail.com','123456', 'Nam', '1999', 'Hà Nội', 2)";
        String insertUser3 = "INSERT INTO User (MaUser, TenUser, Gmail, MatKhau, GioiTinh, NamSinh, DiaChi, role) " +
                "VALUES ('U3', 'user', 'user@gmail.com', '123456', 'Nữ', '1999', 'Hà Nội', 1)";
        String insertUser4 = "INSERT INTO User (MaUser, TenUser, Gmail, MatKhau, GioiTinh, NamSinh, DiaChi, role) " +
                "VALUES ('U4', 'Hieu1', 'Hieu1@gmail.com', '123456', 'Nam', '1999', 'Hà Nội', 1)";
        String insertUser5 = "INSERT INTO User (MaUser, TenUser, Gmail, MatKhau, GioiTinh, NamSinh, DiaChi, role) " +
                "VALUES ('U5', 'Hieu2', 'Hieu2@gmail.com', '123456', 'Nam', '2004', 'Hà Nội', 2)";
        String insertUser6 = "INSERT INTO User (MaUser, TenUser, Gmail, MatKhau, GioiTinh, NamSinh, DiaChi, role) " +
                "VALUES ('U6', 'Hieu3', 'hieu@gmail.com', '123456', 'Nam', '2000', 'Hà Nội', 1)";
        sqLiteDatabase.execSQL(insertUser1);
        sqLiteDatabase.execSQL(insertUser2);
        sqLiteDatabase.execSQL(insertUser3);
        sqLiteDatabase.execSQL(insertUser4);
        sqLiteDatabase.execSQL(insertUser5);
        sqLiteDatabase.execSQL(insertUser6);

        // Insert sample data into PlayList
        String insertPlayList1 = "INSERT INTO PlayList (MaPlayList, MaUser) VALUES ('PL1', 'U1')";
        String insertPlayList2 = "INSERT INTO PlayList (MaPlayList, MaUser) VALUES ('PL2', 'U2')";
        sqLiteDatabase.execSQL(insertPlayList1);
        sqLiteDatabase.execSQL(insertPlayList2);

        // Insert sample data into DanhSachPlaylist
        String insertDanhSachPlaylist1 = "INSERT INTO DanhSachPlaylist (MaDanhSachPlayList, MaNhac, TenDSPlaylist, HINH) " +
                "VALUES ('DSP1', 'N1', 'New music Friday VN', 'album1')";
        String insertDanhSachPlaylist2 = "INSERT INTO DanhSachPlaylist (MaDanhSachPlayList, MaNhac, TenDSPlaylist, HINH) " +
                "VALUES ('DSP2', 'N2', 'Discover Weekly', 'album2')";
        String insertDanhSachPlaylist3 = "INSERT INTO DanhSachPlaylist (MaDanhSachPlayList, MaNhac, TenDSPlaylist, HINH) " +
                "VALUES ('DSP3', 'N3', 'Mới Ra Lò', 'album3')";
        String insertDanhSachPlaylist4 = "INSERT INTO DanhSachPlaylist (MaDanhSachPlayList, MaNhac, TenDSPlaylist, HINH) " +
                "VALUES ('DSP4', 'N4', 'Đẳng Cấp', 'album4')";
        String insertDanhSachPlaylist5 = "INSERT INTO DanhSachPlaylist (MaDanhSachPlayList, MaNhac, TenDSPlaylist, HINH) " +
                "VALUES ('DSP5', 'N5', 'Yêu Thích', 'album5')";
        sqLiteDatabase.execSQL(insertDanhSachPlaylist1);
        sqLiteDatabase.execSQL(insertDanhSachPlaylist2);
        sqLiteDatabase.execSQL(insertDanhSachPlaylist3);
        sqLiteDatabase.execSQL(insertDanhSachPlaylist4);
        sqLiteDatabase.execSQL(insertDanhSachPlaylist5);

        String yeuthich = "INSERT INTO YeuThich (MaUser, MaNhac) " +
                "VALUES ('U3', 'N1')";
        sqLiteDatabase.execSQL(yeuthich);
    }
    public ArrayList<String> getArtistIds(){
        ArrayList<String> artistIds = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CaSi", null);
        if (cursor.moveToFirst()){
            do{
                artistIds.add(cursor.getString(0));

            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
    return artistIds;
    }

}