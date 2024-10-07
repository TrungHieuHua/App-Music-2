package com.example.Music_App.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.Music_App.Activity.Screen_listening_music;
import com.example.Music_App.Adapter.MusicAdapter;
import com.example.Music_App.Adapter.casiAdapter;
import com.example.Music_App.DAO.casiDAO;
import com.example.Music_App.DAO.nhacDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Casi;
import com.example.Music_App.model.Nhac;

import java.util.ArrayList;
import java.util.List;

public class Trangchu_fm extends Fragment {

    private RecyclerView rcvnghesi, rcvtop;
    private casiDAO casiDAO;
    private Button btn_allSong;
    private nhacDAO nhacDAO;
    private MusicAdapter adapternhac;
    private SharedPreferences sharedPreferences;
    private String userId; // Thêm biến userId

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);

        rcvnghesi = view.findViewById(R.id.rcvnghesi);
        btn_allSong = view.findViewById(R.id.btn_allSong);

        // Lấy thông tin người dùng
        sharedPreferences = getActivity().getSharedPreferences("dataUser", MODE_PRIVATE);
        userId = sharedPreferences.getString("user_id", null); // Lấy userId từ SharedPreferences

        casiDAO = new casiDAO(getContext());
        ArrayList<Casi> list = casiDAO.getcasi();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rcvnghesi.setLayoutManager(manager);
        casiAdapter adapter = new casiAdapter(list, getContext());
        rcvnghesi.setAdapter(adapter);

        rcvtop = view.findViewById(R.id.rvNhac);

        //data
        nhacDAO = new nhacDAO(getContext());
        List<Nhac> listnhac = nhacDAO.getSongArtistList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvtop.setLayoutManager(linearLayoutManager);
        adapternhac = new MusicAdapter(getContext(), listnhac, userId); // Truyền userId vào MusicAdapter
        rcvtop.setAdapter(adapternhac);

        // Đặt trình nghe sự kiện khi bấm vào item để mở Screen_listening_music
        adapternhac.setOnItemClickListener(new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), Screen_listening_music.class);
                intent.putExtra("playlist", new ArrayList<>(listnhac)); // Truyền danh sách phát
                intent.putExtra("currentTrackIndex", position); // Truyền vị trí bài hát hiện tại
                startActivity(intent);
            }
        });

//        btn_allSong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), DanhSachBaiHat.class);
//                intent.putExtra("all_songs", true);
//                startActivity(intent);
//            }
//        });

        return view;
    }
}
