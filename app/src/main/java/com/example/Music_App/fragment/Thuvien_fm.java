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
import android.widget.SearchView;
import android.widget.TextView;

import com.example.Music_App.Activity.Screen_ListSong_YeuThich;
import com.example.Music_App.Activity.Screen_ThuVien_NgheSi;
import com.example.Music_App.Activity.Screen_listening_music;
import com.example.Music_App.Adapter.MusicAdapter;
import com.example.Music_App.DAO.nhacDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Nhac;

import java.util.ArrayList;
import java.util.List;

public class Thuvien_fm extends Fragment {

    private RecyclerView recyclerView;
    private List<Nhac> list;
    private nhacDAO nhac_DAO;
    private SharedPreferences sharedPreferences;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thuvien, container, false);

        TextView tvListNgheSi = view.findViewById(R.id.tvListNgheSi);
        TextView tvListyeuThich = view.findViewById(R.id.tvListYeuThich);
        recyclerView = view.findViewById(R.id.rcvlistDanhsachnhac);

        // Lấy thông tin người dùng
        sharedPreferences = getActivity().getSharedPreferences("dataUser", MODE_PRIVATE);
        userId = sharedPreferences.getString("user_id", null);

        // Khởi tạo danh sách bài hát
        list = new ArrayList<>();
        nhac_DAO = new nhacDAO(getContext());
        list = nhac_DAO.getSongArtistList();

        // Thiết lập RecyclerView
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);

        // Khởi tạo và thiết lập adapter
        MusicAdapter adapter = new MusicAdapter(getContext(), list, userId);
        recyclerView.setAdapter(adapter);

        // Thiết lập SearchView
        SearchView edt_timkiem = view.findViewById(R.id.edt_timkiem);
        edt_timkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });

        // Thiết lập sự kiện click cho item
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(getContext(), Screen_listening_music.class);
            intent.putExtra("playlist", (ArrayList<Nhac>) list);
            intent.putExtra("currentTrackIndex", position);
            startActivity(intent);
        });

        // Sự kiện click cho danh sách nghệ sĩ
        tvListNgheSi.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), Screen_ThuVien_NgheSi.class);
            startActivity(intent);
        });

        // Sự kiện click cho danh sách yêu thích
        tvListyeuThich.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), Screen_ListSong_YeuThich.class);
            startActivity(intent);
        });

        return view;
    }
}
