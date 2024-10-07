package com.example.Music_App.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.Music_App.R;
import com.example.Music_App.database.Dbhelper;
import com.example.Music_App.fragment.Thuvien_fm;

import java.util.ArrayList;

public class Screen_Next extends AppCompatActivity {

    private ImageView ivHinhCasi1, ivHinhCasi2, ivHinhCasi3, ivHinhCasi4, ivHinhCasi5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_next);
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen_Next.this, TRANGCHU.class);
                startActivity(intent);

            }
        });

        Dbhelper dbhelper = new Dbhelper(this);
        ArrayList<String> artistIds = dbhelper.getArtistIds();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("MaCaSi", artistIds);
        Thuvien_fm fragment = new Thuvien_fm();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).commit();

        ivHinhCasi1 = findViewById(R.id.ivHinhCasi1);
        ivHinhCasi2 = findViewById(R.id.ivHinhCasi2);
        ivHinhCasi3 = findViewById(R.id.ivHinhCasi3);
        ivHinhCasi4 = findViewById(R.id.ivHinhCasi4);
        ivHinhCasi5 = findViewById(R.id.ivHinhCasi5);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ArrayList<String> selectedArtistIds = getIntent().getStringArrayListExtra("selectedArtistIds");
//        Bundle bundle = new Bundle();
        bundle.putStringArrayList("MaCaSi",selectedArtistIds);

        getSupportFragmentManager().beginTransaction().replace(R.id.main, new Fragment()).commit();
        if (selectedArtistIds != null && selectedArtistIds.size() == 5) {
            ivHinhCasi1.setImageResource(getResources().getIdentifier(selectedArtistIds.get(0), "drawable", getPackageName()));
            ivHinhCasi2.setImageResource(getResources().getIdentifier(selectedArtistIds.get(1), "drawable", getPackageName()));
            ivHinhCasi3.setImageResource(getResources().getIdentifier(selectedArtistIds.get(2), "drawable", getPackageName()));
            ivHinhCasi4.setImageResource(getResources().getIdentifier(selectedArtistIds.get(3), "drawable", getPackageName()));
            ivHinhCasi5.setImageResource(getResources().getIdentifier(selectedArtistIds.get(4), "drawable", getPackageName()));
        }

    }
}
