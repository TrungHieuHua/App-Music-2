package com.example.Music_App.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Music_App.Adapter.TheLoaiAdapter;
import com.example.Music_App.DAO.TheLoaiDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.TheLoai;

import java.util.ArrayList;

public class LoainhacAdmin_fm extends Fragment {

    private RecyclerView rcvtheloaiadmin;
    private TheLoaiDAO theLoaiDAO;
    private TheLoaiAdapter adapter;
    private ArrayList<TheLoai> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loainhac_admin_fm, container, false);

        rcvtheloaiadmin = view.findViewById(R.id.rcvtheloaiadmin);
        SearchView edt_timkiem = view.findViewById(R.id.edt_timkiem);
        ImageButton btnthemtvadmin = view.findViewById(R.id.btnthemtvadmin);

        theLoaiDAO = new TheLoaiDAO(getContext());
        list = theLoaiDAO.getTheLoai();

        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        rcvtheloaiadmin.setLayoutManager(manager);
        adapter = new TheLoaiAdapter(getContext(), list);
        rcvtheloaiadmin.setAdapter(adapter);

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

        btnthemtvadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTheLoaiDialog();
            }
        });

        return view;
    }

    private void showAddTheLoaiDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_theloai, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thêm thể loại")
                .setView(dialogView)
                .setPositiveButton("Thêm", (dialog, which) -> {
                    EditText edtMaLoai = dialogView.findViewById(R.id.edtMaLoai);
                    EditText edtTenLoai = dialogView.findViewById(R.id.edtTenLoai);
                    EditText edtMauTheLoai = dialogView.findViewById(R.id.edtMauTheLoai);

                    String maLoaiStr = edtMaLoai.getText().toString().trim();
                    String tenLoai = edtTenLoai.getText().toString().trim();
                    String mauTheLoai = edtMauTheLoai.getText().toString().trim();

                    if (maLoaiStr.isEmpty() || tenLoai.isEmpty() || mauTheLoai.isEmpty()) {
                        Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            String maLoai = maLoaiStr;
                            TheLoai newTheLoai = new TheLoai(maLoai, tenLoai, mauTheLoai);

                            boolean isAdded = theLoaiDAO.themTheLoai(newTheLoai);

                            if (isAdded) {
                                list.clear();
                                list.addAll(theLoaiDAO.getTheLoai());
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Thêm thể loại thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Thêm thể loại thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(getContext(), "Mã loại phải là số", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

}
