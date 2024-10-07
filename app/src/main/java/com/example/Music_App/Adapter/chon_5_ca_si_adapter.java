package com.example.Music_App.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.R;
import com.example.Music_App.model.Casi;

import java.util.ArrayList;
import java.util.List;

public class chon_5_ca_si_adapter extends RecyclerView.Adapter<chon_5_ca_si_adapter.ViewHolder> {
    private List<Casi> list;
    private Context context;
    private List<Casi> selectedArtists = new ArrayList<>();

    public chon_5_ca_si_adapter(List<Casi> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_chon5_nghesi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Casi artist = list.get(position);
        holder.ten_ca_si.setText(artist.getTenCaSi());
        String imgName = String.valueOf(list.get(position).getHinhCaSi());
        int resID = ((Activity)context).getResources().getIdentifier(imgName,"drawable",((Activity)context).getPackageName());
        holder.img_anh.setImageResource(resID);


        holder.itemView.setOnClickListener(v -> {
            if (selectedArtists.contains(artist)) {
                selectedArtists.remove(artist);
                holder.itemView.setBackgroundColor(Color.parseColor("#40454E")); // Bỏ chọn
            } else {
                if (selectedArtists.size() < 5) {
                    selectedArtists.add(artist);
                    holder.itemView.setBackgroundColor(Color.LTGRAY); // Chọn
                } else {
                    Toast.makeText(context, "Bạn chỉ có thể chọn 5 nghệ sĩ", Toast.LENGTH_SHORT).show();
                }
            }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<Casi> getSelectedArtists() {
        return selectedArtists;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_anh;
        TextView ten_ca_si;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_anh = itemView.findViewById(R.id.anh_chon_nghe_si);
            ten_ca_si = itemView.findViewById(R.id.ten_nghe_si);
        }
    }
}
