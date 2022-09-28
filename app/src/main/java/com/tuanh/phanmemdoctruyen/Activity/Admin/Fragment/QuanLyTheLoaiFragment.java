package com.tuanh.phanmemdoctruyen.Activity.Admin.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTheLoai.TheLoaiAdapter;
import com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTheLoai.ThemTheLoaiActivity;
import com.tuanh.phanmemdoctruyen.DAO.TheLoaiDAO;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;
import java.util.stream.Collectors;

public class QuanLyTheLoaiFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_thu_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        onClick();
        onLoadTheLoai();
    }

    Button btnThem;
    AutoCompleteTextView edtTimKiem;
    TheLoaiAdapter adapter;
    List<TheLoai> theLoaiList;
    TheLoaiDAO theLoaiDAO;
    RecyclerView rcvTheLoai;
    ArrayAdapter<TheLoai> theLoaiArrayAdapter;

    private void anhXa(View view) {
        btnThem = view.findViewById(R.id.btn_them);
        rcvTheLoai = view.findViewById(R.id.rcv_the_loai);
        rcvTheLoai.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        theLoaiDAO = new TheLoaiDAO(getContext());
        theLoaiList = theLoaiDAO.dsLoai();
        adapter = new TheLoaiAdapter(getContext(), theLoaiList);
        rcvTheLoai.setAdapter(adapter);
        theLoaiArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, theLoaiList);
        edtTimKiem = view.findViewById(R.id.edt_tim_kiem);
        edtTimKiem.setAdapter(theLoaiArrayAdapter);

    }

    private void onClick() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ThemTheLoaiActivity.class));
            }
        });
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter = new TheLoaiAdapter(getContext(), timKiem(edtTimKiem.getText().toString()));
                rcvTheLoai.setAdapter(adapter);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter = new TheLoaiAdapter(getContext(), timKiem(edtTimKiem.getText().toString()));
                rcvTheLoai.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter = new TheLoaiAdapter(getContext(), timKiem(edtTimKiem.getText().toString()));
                rcvTheLoai.setAdapter(adapter);
            }
        });
    }

    private void onLoadTheLoai() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (theLoaiList.size() != theLoaiDAO.dsLoai().size()) {
                    theLoaiList = theLoaiDAO.dsLoai();
                    adapter = new TheLoaiAdapter(getContext(), theLoaiList);
                    rcvTheLoai.setAdapter(adapter);
                }
                handler.postDelayed(this, 300);
            }
        };
        handler.post(runnable);
    }

    private List<TheLoai> timKiem(String tuKhoa) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return theLoaiList.stream().filter(theLoai -> theLoai.getTenLoai().toLowerCase().contains(tuKhoa.toLowerCase())).collect(Collectors.toList());
        }
        return theLoaiList;
    }
}