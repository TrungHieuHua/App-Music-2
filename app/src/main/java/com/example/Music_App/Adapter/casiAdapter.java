package com.example.Music_App.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.Activity.DanhSachBaiHat;
import com.example.Music_App.R;
import com.example.Music_App.model.Casi;

import java.util.ArrayList;

public class casiAdapter extends RecyclerView.Adapter<casiAdapter.ViewHoder>{


    private Context context;
    private ArrayList<Casi> list;

    public casiAdapter(ArrayList<Casi> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_casi, parent, false);

        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull casiAdapter.ViewHoder holder, int position) {
        Casi casi = list.get(position);
  //      holder.name.setText(list.get(position).getTenCaSi());
        holder.ten.setText(list.get(position).getTenCaSi());
        String imgName = String.valueOf(list.get(position).getHinhalbum());
        int resID = ((Activity)context).getResources().getIdentifier(imgName,"drawable",((Activity)context).getPackageName());
        holder.backgroundcasi.setImageResource(resID);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHat.class);
                intent.putExtra("ten_nghe_si", casi.getTenCaSi());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHoder extends RecyclerView.ViewHolder {

        // khai bao cac component co o trong item

        TextView name , ten;
        ImageView backgroundcasi;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            // anh xa

            name = itemView.findViewById(R.id.name);
            ten = itemView.findViewById(R.id.ten);
            backgroundcasi = itemView.findViewById(R.id.backgroundcasi);

        }


    }
}
