package com.example.Music_App.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.Adapter.MusicAdapter;
import com.example.Music_App.Adapter.ThuVien_NgheSi_Adapter;
import com.example.Music_App.DAO.casiDAO;
import com.example.Music_App.DAO.nhacDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Casi;

import java.util.ArrayList;

public class Screen_ThuVien_NgheSi extends AppCompatActivity {


    private com.example.Music_App.DAO.casiDAO casiDAO;
    private RecyclerView rcvnghesi, rcvtop;
    private com.example.Music_App.DAO.nhacDAO nhacDAO;
    private MusicAdapter adapternhac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_thu_vien_nghe_si);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SearchView edt_timkiem = findViewById(R.id.edt_timkiem);
        rcvnghesi = findViewById(R.id.rcvnghesi);

        casiDAO = new casiDAO(this);
        ArrayList<Casi> list = casiDAO.getcasi();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
//        manager.setOrientation(RecyclerView.VERTICAL);
        rcvnghesi.setLayoutManager(manager);
        ThuVien_NgheSi_Adapter adapter = new ThuVien_NgheSi_Adapter(this,list);
        rcvnghesi.setAdapter(adapter);
        nhacDAO = new nhacDAO(this);

//        List<Nhac> listnhac = nhacDAO.getSongArtistList();

        edt_timkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });




    }
}