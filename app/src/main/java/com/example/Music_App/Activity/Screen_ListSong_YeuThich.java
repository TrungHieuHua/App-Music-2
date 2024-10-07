package com.example.Music_App.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.Adapter.MusicAdapter;
import com.example.Music_App.DAO.nhacDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Nhac;

import java.util.ArrayList;
import java.util.List;

public class Screen_ListSong_YeuThich extends AppCompatActivity {

    private List<Nhac> list;
    private nhacDAO nhac_DAO;
    private ImageView back;
    private RecyclerView recyclerView;
    private MusicAdapter adapter;
    private SharedPreferences sharedPreferences;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_list_song_yeu_thich);

        sharedPreferences = getSharedPreferences("dataUser", MODE_PRIVATE);
        userId = sharedPreferences.getString("user_id", null);

        SearchView edt_timkiem = findViewById(R.id.edt_timkiem);
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.rvList_yeuthich);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back.setOnClickListener(view -> finish());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Khởi tạo DAO và nạp danh sách bài hát yêu thích
        nhac_DAO = new nhacDAO(this);
        loadAllData();

        // Thiết lập SearchView
        edt_timkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    private void loadAllData() {
        list = nhac_DAO.getSongYeuThich(userId);

        adapter = new MusicAdapter(this, list, userId);
        recyclerView.setAdapter(adapter);

        // Thiết lập sự kiện cho item click
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(Screen_ListSong_YeuThich.this, Screen_listening_music.class);
            intent.putExtra("playlist", (ArrayList<Nhac>) list); // Truyền danh sách phát
            intent.putExtra("currentTrackIndex", position); // Truyền vị trí bài hát hiện tại
            startActivity(intent);
        });
    }
}
