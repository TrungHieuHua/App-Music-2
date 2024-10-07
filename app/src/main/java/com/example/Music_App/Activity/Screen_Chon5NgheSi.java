package com.example.Music_App.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.Music_App.Adapter.chon_5_ca_si_adapter;
import com.example.Music_App.DAO.casiDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Casi;

import java.util.ArrayList;

public class Screen_Chon5NgheSi extends AppCompatActivity {

    private ArrayList<Casi> list;
    private RecyclerView rcv_chon_ca_si;
    private casiDAO casiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_screen_chon5_nghe_si);

        rcv_chon_ca_si = findViewById(R.id.rcViewchon5NgheSi);
        casiDAO = new casiDAO(this);
        Button btn_chon_tiep = findViewById(R.id.btn_chon_tiep);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        list = casiDAO.getcasi();

        rcv_chon_ca_si.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        chon_5_ca_si_adapter adapter = new chon_5_ca_si_adapter(list, Screen_Chon5NgheSi.this);
        rcv_chon_ca_si.setAdapter(adapter);

        btn_chon_tiep.setOnClickListener(v -> {
            ArrayList<String> selectedArtistIds = new ArrayList<>();
            for (Casi artist : adapter.getSelectedArtists()) {
                selectedArtistIds.add(artist.getHinhCaSi());
            }
            if (selectedArtistIds.size() == 5) {
                Intent intent = new Intent(Screen_Chon5NgheSi.this, Screen_Next.class);
                intent.putStringArrayListExtra("selectedArtistIds", selectedArtistIds);
                startActivity(intent);
            } else {
                Toast.makeText(Screen_Chon5NgheSi.this, "Bạn phải chọn đủ 5 nghệ sĩ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
