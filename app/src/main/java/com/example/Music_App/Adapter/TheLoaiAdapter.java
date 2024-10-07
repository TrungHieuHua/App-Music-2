package com.example.Music_App.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.Activity.Screen_nhacTheotheloai;
import com.example.Music_App.DAO.TheLoaiDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.TheLoai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHoder> implements Filterable {

    private Context context;
    private List<TheLoai> list;
    private List<TheLoai> filteredList;
    private TheLoaiDAO theLoaiDAO;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> list) {
        this.context = context;
        this.list = list;
        this.filteredList = list;
        this.theLoaiDAO = new TheLoaiDAO(context); // Khởi tạo DAO
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_theloai, parent, false);
        return new ViewHoder(view);
    }

    @SuppressLint({"RecyclerView", "NewApi"})
    @Override
    public void onBindViewHolder(@NonNull TheLoaiAdapter.ViewHoder holder, int position) {
        TheLoai theloai = list.get(position);
        holder.name.setText(list.get(position).getTenLoai());
        holder.card.setCardBackgroundColor(holder.itemView.getResources().getColor(getRandomColor(), null));
        SharedPreferences sharedPreferences = context.getSharedPreferences("dataUser", MODE_PRIVATE);
        int role = sharedPreferences.getInt("role", -1);

        // Chỉ xử lý sự kiện click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Screen_nhacTheotheloai.class);
                intent.putExtra("ten_loai",theloai.getTenLoai());
                context.startActivity(intent);
            }
        });

        // Xử lý long click chỉ khi role là 2
        if (role == 2) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showPopupMenu(v, position);
                    return true;
                }
            });
        } else {
            // Nếu role không phải là 2, không có hành động gì khi long click
            holder.itemView.setOnLongClickListener(null);
        }
    }

    private void showPopupMenu(View view, int position) {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_admin_theloai, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_edit) {
                    // Xử lý hành động sửa
                    editItem(position);
                    return true;
                } else if (id == R.id.action_delete) {
                    // Xử lý hành động xóa
                    deleteItem(position);
                    return true;
                } else {
                    return false;
                }
            }
        });
        popup.show();
    }

    private void editItem(int position) {
        TheLoai theLoai = list.get(position);

        // Tạo dialog để chỉnh sửa thông tin thể loại
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_theloai, null);
        builder.setView(dialogView);

        EditText edtMaLoai = dialogView.findViewById(R.id.edtMaLoaihh);
        EditText edtTenLoai = dialogView.findViewById(R.id.edtTenLoaihh);
        EditText edtMauTheLoai = dialogView.findViewById(R.id.edtMauTheLoaihh);

        edtMaLoai.setText(String.valueOf(theLoai.getMaLoai()));
        edtTenLoai.setText(theLoai.getTenLoai());
        edtMauTheLoai.setText(theLoai.getMauTheLoai());

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String maLoai = edtMaLoai.getText().toString();
                String tenLoai = edtTenLoai.getText().toString();
                String mauTheLoai = edtMauTheLoai.getText().toString();

                TheLoai updatedTheLoai = new TheLoai(maLoai, tenLoai, mauTheLoai);
                boolean isUpdated = theLoaiDAO.suaTheLoai(updatedTheLoai);

                if (isUpdated) {
                    list.set(position, updatedTheLoai);
                    notifyItemChanged(position);
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void deleteItem(int position) {
        TheLoai theLoai = list.get(position);
        boolean isDeleted = theLoaiDAO.xoaTheLoai(theLoai.getMaLoai()); // Đã sửa


        if (isDeleted) {
            list.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Xóa thể loại thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa thể loại thất bại", Toast.LENGTH_SHORT).show();
        }
    }


    private int getRandomColor() {
        List<Integer> colorList = new ArrayList<>();

        colorList.add(R.color.random2);
        colorList.add(R.color.random3);
        colorList.add(R.color.random4);
        colorList.add(R.color.random5);
        colorList.add(R.color.random7);
        colorList.add(R.color.random8);
        colorList.add(R.color.random10);
        colorList.add(R.color.random11);
        colorList.add(R.color.random12);
        colorList.add(R.color.random13);
        colorList.add(R.color.random14);
        colorList.add(R.color.random15);
        colorList.add(R.color.random16);
        colorList.add(R.color.random17);

        Random random = new Random();
        int number = random.nextInt(colorList.size());

        return colorList.get(number);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        TextView name;
        CardView card;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            card = itemView.findViewById(R.id.card);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString();

                if (filterPattern.isEmpty()) {
                    list = filteredList;
                } else {
                    List<TheLoai> filtered = new ArrayList<>();
                    for (TheLoai item : filteredList) {
                        if (item.getTenLoai().toLowerCase().contains(filterPattern)) {
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
                list = (List<TheLoai>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
