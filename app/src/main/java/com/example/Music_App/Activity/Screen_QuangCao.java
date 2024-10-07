package com.example.Music_App.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Music_App.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Screen_QuangCao extends AppCompatActivity {

    private TextView tvCountdown;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 5000;
    private ImageView imageView;
    private boolean isTimerFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen_quang_cao);

        MobileAds.initialize(this);

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        imageView = findViewById(R.id.imageView);
        tvCountdown = findViewById(R.id.tvCountdown);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        countDownTimer = new CountDownTimer(5000, 1000) { // Đếm ngược mỗi 1 giây
            @Override
            public void onTick(long millisUntilFinished) {
                // Không cần xử lý gì trong onTick nếu bạn không muốn
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                // Đặt cờ để biết rằng đếm ngược đã kết thúc
                isTimerFinished = true;
                // Hiển thị ImageView sau khi đếm ngược kết thúc
                imageView.setVisibility(View.VISIBLE);
                tvCountdown.setVisibility(View.INVISIBLE);
            }
        };

        // Bắt đầu đếm ngược
        countDownTimer.start();
    }

    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        tvCountdown.setText("" + seconds + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Hủy đếm ngược khi Activity hoặc Fragment bị hủy
        }
    }

}