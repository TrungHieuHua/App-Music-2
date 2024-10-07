package com.example.Music_App.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.Activity.DanhSachBaiHat;
import com.example.Music_App.R;
import com.example.Music_App.model.Casi;
import com.example.Music_App.DAO.casiDAO;

import java.util.ArrayList;
import java.util.List;

public class ThuVien_NgheSi_Adapter extends RecyclerView.Adapter<ThuVien_NgheSi_Adapter.ViewHolder> implements Filterable {
    private Context context;
    private List<Casi> list;
    private List<Casi> filteredList;
    private casiDAO casiDAO;

    public ThuVien_NgheSi_Adapter(Context context, List<Casi> list) {
        this.context = context;
        this.list = list;
        this.filteredList = new ArrayList<>(list);
        this.casiDAO = new casiDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nghesi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Casi casi = list.get(position);
        holder.name.setText(list.get(position).getTenCaSi());
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView backgroundcasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            backgroundcasi = itemView.findViewById(R.id.backgroundcasi);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();

                if (filterPattern.isEmpty()) {
                    list = filteredList;
                } else {
                    List<Casi> filtered = new ArrayList<>();
                    for (Casi item : filteredList) {
                        if (item.getTenCaSi().toLowerCase().contains(filterPattern)) {
                            filtered.add(item);
                        }
                    }
                    list = filtered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<Casi>) results.values;
                notifyDataSetChanged();
            }
        };

}}
