package com.example.Music_App.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Music_App.R;

public class Screen_mail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_mail);
        ImageView img_tro_ve = findViewById(R.id.img_tro_ve);
        Button btn_tiep1 = findViewById(R.id.btn_tiep1);
        EditText edt_nhap_email_dk = findViewById(R.id.edt_nhap_mai_dk);

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
                String email = edt_nhap_email_dk.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Screen_mail.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Screen_mail.this, Screen_TaoMatKhau.class);
                    intent.putExtra("email", email); // Thêm dòng này để truyền email
                    startActivity(intent);
                }
            }
        });
    }
}