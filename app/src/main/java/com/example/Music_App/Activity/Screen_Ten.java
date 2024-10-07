package com.example.Music_App.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Music_App.DAO.NguoiDungDAO;
import com.example.Music_App.R;
import com.google.android.material.textfield.TextInputEditText;

public class Screen_Ten extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_ten);

        ImageView imgTroVe = findViewById(R.id.img_tro_ve);
        Button btn_tiepname = findViewById(R.id.btn_tiepname);
        TextInputEditText tvname = findViewById(R.id.tvname);



        imgTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_tiepname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String Gmail = intent.getStringExtra("email");
                String MatKhau = intent.getStringExtra("matKhau");
                String NamSinh = intent.getStringExtra("year");
                String GioiTinh = intent.getStringExtra("gender");
                String TenUser = tvname.getText().toString();
                if (TenUser.length() == 0){
                    Toast.makeText(Screen_Ten.this, "vui long nhap ten", Toast.LENGTH_SHORT).show();
                }else {
                    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(Screen_Ten.this);
                    int check = nguoiDungDAO.Dangky(Gmail, MatKhau, NamSinh, GioiTinh,TenUser);
                    Intent intentnext = new Intent(Screen_Ten.this, Screen_Chon5NgheSi.class);
                    startActivity(intentnext);

                }
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
