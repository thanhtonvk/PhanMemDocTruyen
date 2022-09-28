package com.tuanh.phanmemdoctruyen.Activity.Admin.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen.TruyenAdapter;
import com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen.ThemTruyenActivity;
import com.tuanh.phanmemdoctruyen.DAO.TruyenDAO;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;
import java.util.stream.Collectors;

public class QuanLyTruyenFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_truyen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        onClick();
        onLoadTruyen();
    }

    Button btnThem;
    AutoCompleteTextView edtTimKiem;
    TruyenAdapter adapter;
    List<Truyen> truyenList;
    TruyenDAO truyenDAO;
    RecyclerView rcvTruyen;
    ArrayAdapter<Truyen> truyenArrayAdapter;

    private void anhXa(View view) {
        btnThem = view.findViewById(R.id.btn_them);
        rcvTruyen = view.findViewById(R.id.rcv_truyen);
        truyenDAO = new TruyenDAO(getContext());
        truyenList = truyenDAO.dsTruyen();
        adapter = new TruyenAdapter(truyenList, getContext());
        rcvTruyen.setAdapter(adapter);
        truyenArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, truyenList);
        edtTimKiem = view.findViewById(R.id.edt_tim_kiem);
        edtTimKiem.setAdapter(truyenArrayAdapter);
    }

    private void onClick() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ThemTruyenActivity.class));
            }
        });

        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter = new TruyenAdapter(timKiem(edtTimKiem.getText().toString()), getContext());
                rcvTruyen.setAdapter(adapter);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter = new TruyenAdapter(timKiem(edtTimKiem.getText().toString()), getContext());
                rcvTruyen.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter = new TruyenAdapter(timKiem(edtTimKiem.getText().toString()), getContext());
                rcvTruyen.setAdapter(adapter);
            }
        });
    }

    private void onLoadTruyen() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (truyenList.size() != truyenDAO.dsTruyen().size()) {
                    truyenList = truyenDAO.dsTruyen();
                    adapter = new TruyenAdapter(truyenList, getContext());
                    rcvTruyen.setAdapter(adapter);
                }
                handler.postDelayed(this, 300);
            }
        };
        handler.post(runnable);
    }

    private List<Truyen> timKiem(String tuKhoa) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return truyenList.stream().filter(theLoai -> theLoai.getTenTruyen().contains(tuKhoa)).collect(Collectors.toList());
        }
        return truyenList;
    }
}