package com.example.Music_App.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.Activity.DanhSachBaiHat;
import com.example.Music_App.DAO.casiDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Casi;

import java.util.ArrayList;

public class thuvienAdapter extends RecyclerView.Adapter<thuvienAdapter.ViewHolder> {
    private Context context;

     private ArrayList<Casi> list;
    private casiDAO casiDAO;
    private Uri imageUri; // Khai báo biến imageUri
    private static final int PICK_IMAGE_REQUEST = 1;


    public thuvienAdapter(Context context, ArrayList<Casi> list) {
        this.context = context;
        this.list = list;
        this.casiDAO = new casiDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_listcasi_thuvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Casi currentCasi = list.get(position);
        holder.tvTencasi.setText(currentCasi.getTenCaSi());
        int resID = context.getResources().getIdentifier(currentCasi.getHinhalbum(), "drawable", context.getPackageName());
        holder.iv_hinhCasi.setImageResource(resID);
        SharedPreferences sharedPreferences = context.getSharedPreferences("dataUser", MODE_PRIVATE);
        int role = sharedPreferences.getInt("role", -1);

        // Chỉ xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DanhSachBaiHat.class);
            context.startActivity(intent);
        });



        if (role == 2) {
            holder.itemView.setOnLongClickListener(v -> {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_admin_nghesi, popupMenu.getMenu());


                popupMenu.setOnMenuItemClickListener(item -> {
                    if (item.getItemId() == R.id.action_editns) {
                        showEditDialog(currentCasi);
                        return true;
                    } else if (item.getItemId() == R.id.action_deletens) {
                        showDeleteConfirmationDialog(currentCasi, position);
                        return true;
                    }
                    return false;
                });

                popupMenu.show();
                return true;
            });
        } else {
            // Nếu role không phải là 2, không có hành động gì khi long click
            holder.itemView.setOnLongClickListener(null);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void showDeleteConfirmationDialog(Casi casi, int position) {
        new AlertDialog.Builder(context)
                .setTitle("Xóa nghệ sĩ")
                .setMessage("Bạn có chắc chắn muốn xóa nghệ sĩ này không?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    if (casiDAO.xoaCasi(casi.getMaCaSi())) {
                        list.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Xóa nghệ sĩ thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Xóa nghệ sĩ thất bại", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
    private void showEditDialog(Casi currentCasi) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_sua_nghe_si, null);
        builder.setView(dialogView);

        ImageView imgalbum = dialogView.findViewById(R.id.imgeditalbum);
        EditText edtmanghesi = dialogView.findViewById(R.id.edtmanghesi);
        EditText edttennghesi = dialogView.findViewById(R.id.edttennghesi);
        EditText edtquenghesi = dialogView.findViewById(R.id.edtquenghesi);
        EditText edtnamnghesi = dialogView.findViewById(R.id.edtnamnghesi);
        ImageView imghinhcasi = dialogView.findViewById(R.id.imgedthinhcasi);
        Button btnthemdone = dialogView.findViewById(R.id.btnthemdone);
        Button btnthoat = dialogView.findViewById(R.id.btnthoat);

        imgalbum.setImageURI(Uri.parse(currentCasi.getHinhalbum()));
        edtmanghesi.setText(currentCasi.getMaCaSi());
        edttennghesi.setText(currentCasi.getTenCaSi());
        edtquenghesi.setText(currentCasi.getQueQuan());
        edtnamnghesi.setText(currentCasi.getNamSinh());
        imghinhcasi.setImageURI(Uri.parse(currentCasi.getHinhCaSi()));

        AlertDialog alertDialog = builder.create();

        imgalbum.setOnClickListener(v -> openImageChooser());

        imghinhcasi.setOnClickListener(v -> openImageChooser());

        btnthemdone.setOnClickListener(v -> {
            String maNgheSi = edtmanghesi.getText().toString().trim();
            String tenNgheSi = edttennghesi.getText().toString().trim();
            String queNgheSi = edtquenghesi.getText().toString().trim();
            String namNgheSi = edtnamnghesi.getText().toString().trim();

            if (imageUri == null) {
                Toast.makeText(context, "Vui lòng chọn hình ảnh", Toast.LENGTH_SHORT).show();
                return;
            }

            currentCasi.setHinhCaSi(imageUri.toString());
            currentCasi.setMaCaSi(maNgheSi);
            currentCasi.setTenCaSi(tenNgheSi);
            currentCasi.setQueQuan(queNgheSi);
            currentCasi.setNamSinh(namNgheSi);

            int result = casiDAO.capNhatCasi(currentCasi);

            if (result > 0) {
                notifyDataSetChanged();
                Toast.makeText(context, "Sửa nghệ sĩ thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Sửa nghệ sĩ thất bại", Toast.LENGTH_SHORT).show();
            }

            alertDialog.dismiss();
        });

        btnthoat.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog.show();
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity)context).startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTencasi;
        ImageView iv_hinhCasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTencasi = itemView.findViewById(R.id.tvTenCasi);
            iv_hinhCasi = itemView.findViewById(R.id.iv_nghesi_thuvien);
        }
    }
}
