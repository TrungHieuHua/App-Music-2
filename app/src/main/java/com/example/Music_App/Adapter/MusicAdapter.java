package com.example.Music_App.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.DAO.nhacDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Nhac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Nhac> list;
    private nhacDAO nhacDAO;
    private List<Nhac> filteredList;
    private OnItemClickListener onItemClickListener;
    private Map<Integer, Integer> clickCountMap = new HashMap<>();
    private boolean isImageOne = true;
    private String maUser;

    public MusicAdapter(Context context, List<Nhac> list, String maUser) {
        this.context = context;
        this.list = list;
        this.nhacDAO = new nhacDAO(context);
        this.filteredList = new ArrayList<>(list);
        this.maUser = maUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_bai_nhac, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Nhac nhac = list.get(position);
        holder.tvTenNhac.setText(nhac.getTenNhac());
        holder.tvNgheSi.setText(nhac.getTenCaSi());
        SharedPreferences sharedPreferences = context.getSharedPreferences("dataUser", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);
        String imgNhac = nhac.getHinhNhac();
        int resID = context.getResources().getIdentifier(imgNhac, "drawable", context.getPackageName());
        holder.imgNhac.setImageResource(resID);

        holder.imLove.setOnClickListener(view -> {
            if (isImageOne) {
                holder.imLove.setImageResource(R.drawable.love);
                nhacDAO.addYeuThich(userId, nhac.getMaNhac());
                showToast("Bạn đã thêm bài hát vào Danh Sách Yêu Thích");
            } else {
                holder.imLove.setImageResource(R.drawable.love1);
                nhacDAO.removeYeuThich(userId, nhac.getMaNhac());
                showToast("Bạn đã bỏ bài hát ra khỏi Danh Sách Yêu Thích");
            }
            isImageOne = !isImageOne;
        });

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(pos);
                }
            }
        });
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.LEFT | Gravity.TOP, 20, 30);
        toast.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                List<Nhac> filtered = new ArrayList<>();
                if (filterPattern.isEmpty()) {
                    filtered.addAll(filteredList);
                } else {
                    for (Nhac item : filteredList) {
                        if (item.getTenNhac().toLowerCase().contains(filterPattern)) {
                            filtered.add(item);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list.clear();
                list.addAll((List<Nhac>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public void updateData(List<Nhac> songArtistList) {
        this.list.clear();
        this.list.addAll(songArtistList);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNhac, imLove;
        TextView tvTenNhac;
        TextView tvNgheSi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNhac = itemView.findViewById(R.id.img_item_listnhac);
            tvTenNhac = itemView.findViewById(R.id.tvTenNhac);
            tvNgheSi = itemView.findViewById(R.id.tvNgheSi);
            imLove = itemView.findViewById(R.id.imgLove);
        }
    }
}
