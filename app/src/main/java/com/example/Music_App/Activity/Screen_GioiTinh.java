package com.example.Music_App.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Music_App.R;

public class Screen_GioiTinh extends AppCompatActivity {

    private Button btnNu, btnNam, btnPhiGioi, btnKhac, btnNo, btnTiepTuc;
    private String selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_gioi_tinh);

        ImageView img_tro_ve = findViewById(R.id.img_tro_ve);
        btnNu = findViewById(R.id.btnnu);
        btnNam = findViewById(R.id.btnnam);
        btnPhiGioi = findViewById(R.id.btnphigioi);
        btnKhac = findViewById(R.id.btnkhac);
        btnNo = findViewById(R.id.btnno);
        btnTiepTuc = findViewById(R.id.tieptuc);
        btnTiepTuc.setEnabled(false);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String matKhau = intent.getStringExtra("matKhau");
        String year = intent.getStringExtra("year");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        img_tro_ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        View.OnClickListener genderClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender = ((Button) v).getText().toString();
                btnNu.setVisibility(View.GONE);
                btnNam.setVisibility(View.GONE);
                btnPhiGioi.setVisibility(View.GONE);
                btnKhac.setVisibility(View.GONE);
                btnNo.setVisibility(View.GONE);
                v.setVisibility(View.VISIBLE);
                v.setBackgroundColor(getResources().getColor(R.color.colorbtngioitinh)); // Đổi màu button đã chọn
                btnTiepTuc.setEnabled(true);
            }
        };

        btnNu.setOnClickListener(genderClickListener);
        btnNam.setOnClickListener(genderClickListener);
        btnPhiGioi.setOnClickListener(genderClickListener);
        btnKhac.setOnClickListener(genderClickListener);
        btnNo.setOnClickListener(genderClickListener);

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Screen_GioiTinh.this, Screen_Ten.class);
                intent.putExtra("email", email);
                intent.putExtra("matKhau", matKhau);
                intent.putExtra("year", year);
                intent.putExtra("gender", selectedGender);
                startActivity(intent);
            }
        });
    }
}
