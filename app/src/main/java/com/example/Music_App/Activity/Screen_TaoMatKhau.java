package com.example.Music_App.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Music_App.R;
import com.google.android.material.textfield.TextInputEditText;

public class Screen_TaoMatKhau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_tao_mat_khau);

        ImageView img_tro_ve = findViewById(R.id.img_tro_ve);
        Button btn_tiep1 = findViewById(R.id.btn_tiep1);
        TextInputEditText edtmk = findViewById(R.id.edtmk);

        // Nhận email từ Intent
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        img_tro_ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_tiep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matKhau = edtmk.getText().toString().trim();

                // Tạo Intent để chuyển đến Screen_Ngay
                Intent intent = new Intent(Screen_TaoMatKhau.this, Screen_Ngay.class);
                intent.putExtra("email", email); // Truyền email
                intent.putExtra("matKhau", matKhau); // Truyền mật khẩu
                startActivity(intent);
            }
        });
    }

}