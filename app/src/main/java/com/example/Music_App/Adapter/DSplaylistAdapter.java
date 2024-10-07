package com.example.Music_App.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.R;
import com.example.Music_App.model.DSplaylist;

import java.util.ArrayList;

public class DSplaylistAdapter extends RecyclerView.Adapter<DSplaylistAdapter.ViewHoder>{

    private Context context;
    private ArrayList<DSplaylist> list;

    public DSplaylistAdapter(Context context, ArrayList<DSplaylist> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhac, parent, false);

        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSplaylistAdapter.ViewHoder holder, int position) {
        holder.name.setText(list.get(position).getTenNhac());
        String imgName = String.valueOf(list.get(position).getHinhNhac());
        int resID = ((Activity)context).getResources().getIdentifier(imgName,"drawable",((Activity)context).getPackageName());
        holder.img.setImageResource(resID);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHoder extends RecyclerView.ViewHolder {

        // khai bao cac component co o trong item

        TextView name ;
        ImageView img;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            // anh xa

            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);

        }


    }
}
