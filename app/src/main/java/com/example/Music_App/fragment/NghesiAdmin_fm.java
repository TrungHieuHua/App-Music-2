package com.example.Music_App.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Music_App.Adapter.thuvienAdapter;
import com.example.Music_App.DAO.casiDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Casi;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class NghesiAdmin_fm extends Fragment {

    static final int PICK_IMAGE_REQUEST = 1;

    private RecyclerView rvlistCasiadmin;
    private casiDAO casiDAO;
    private Uri imageUri;
    private ArrayList<Casi> list;
    private thuvienAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nghesi_admin_fm, container, false);

        rvlistCasiadmin = view.findViewById(R.id.rvListCaSiadmin);
        casiDAO = new casiDAO(getContext());

        list = casiDAO.getcasi();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rvlistCasiadmin.setLayoutManager(manager);

        adapter = new thuvienAdapter(getContext(), list);
        rvlistCasiadmin.setAdapter(adapter);

        ImageButton btnthemnsadmin = view.findViewById(R.id.btnthemnsadmin);
        btnthemnsadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddArtistDialog();
            }
        });

        return view;
    }

    private void showAddArtistDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_them_nghesi, null);
        builder.setView(dialogView);

        ImageView imgalbum = dialogView.findViewById(R.id.imgalbum);
        EditText edtmanghesi = dialogView.findViewById(R.id.edtmanghesi);
        EditText edttennghesi = dialogView.findViewById(R.id.edttennghesi);
        EditText edtquenghesi = dialogView.findViewById(R.id.edtquenghesi);
        EditText edtnamnghesi = dialogView.findViewById(R.id.edtnamnghesi);
        ImageView imghinhcasi = dialogView.findViewById(R.id.imghinhcasi);
        Button btnthemdone = dialogView.findViewById(R.id.btnthemdone);
        Button btnthoat = dialogView.findViewById(R.id.btnthoat);

        AlertDialog alertDialog = builder.create();
        imgalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        imghinhcasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        btnthemdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNgheSi = edtmanghesi.getText().toString().trim();
                String tenNgheSi = edttennghesi.getText().toString().trim();
                String queNgheSi = edtquenghesi.getText().toString().trim();
                String namNgheSi = edtnamnghesi.getText().toString().trim();

                if (imageUri == null) {
                    Toast.makeText(getContext(), "Vui lòng chọn hình ảnh", Toast.LENGTH_SHORT).show();
                    return;
                }

                Casi newCasi = new Casi(imageUri.toString() ,maNgheSi, tenNgheSi, queNgheSi, namNgheSi, imageUri.toString());
                long result = casiDAO.themCasi(newCasi);

                if (result != -1) {
                    list.add(newCasi);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm nghệ sĩ thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm nghệ sĩ thất bại", Toast.LENGTH_SHORT).show();
                }

                alertDialog.dismiss();
            }
        });

        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            ImageView imageView = getView().findViewById(R.id.iv_nghesi_thuvien);
            imageView.setImageURI(imageUri);
        }
    }
}
