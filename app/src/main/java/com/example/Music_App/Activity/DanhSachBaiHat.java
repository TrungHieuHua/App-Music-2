package com.example.Music_App.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.Music_App.Adapter.MusicAdapter;
import com.example.Music_App.DAO.nhacDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Nhac;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DanhSachBaiHat extends AppCompatActivity {
    private List<Nhac> list;
    private RecyclerView rcv_list_danh_sach_nhac;
    private nhacDAO nhac_DAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_danh_sach_bai_hat);

        ImageView back = findViewById(R.id.back);
        rcv_list_danh_sach_nhac = findViewById(R.id.rcvlistDanhsachnhac);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        list = new ArrayList<>();
        nhac_DAO = new nhacDAO(this);

        Intent intent = getIntent();
        boolean showAllSongs = intent.getBooleanExtra("all_songs", false);

        if (showAllSongs) {
            loadAllData();
        } else {
            String artistName = intent.getStringExtra("ten_nghe_si");
            loadDataByArtist(artistName);
        }
    }

    private void loadDataByArtist(String artistName) {
        list = nhac_DAO.getSongsByArtist(artistName);
        setupRecyclerView();
    }

    private void loadAllData() {
        list = nhac_DAO.getSongArtistList();
        Collections.shuffle(list);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataUser", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null); // hoặc giá trị mặc định nếu không tìm thấy

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rcv_list_danh_sach_nhac.setLayoutManager(manager);

        MusicAdapter adapter = new MusicAdapter(this, list, userId);
        rcv_list_danh_sach_nhac.setAdapter(adapter);

        adapter.setOnItemClickListener(new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(DanhSachBaiHat.this, Screen_listening_music.class);
                intent.putExtra("playlist", (ArrayList<Nhac>) list); // Truyền danh sách phát
                intent.putExtra("currentTrackIndex", position); // Truyền vị trí bài hát hiện tại
                startActivity(intent);
            }
        });
    }

}
