package com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen.QuanLyTap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen.ThemTruyenActivity;
import com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen.TruyenAdapter;
import com.tuanh.phanmemdoctruyen.DAO.TapTruyenDAO;
import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.sql.Array;
import java.util.List;
import java.util.stream.Collectors;

public class QuanLyTapTruyenActivity extends AppCompatActivity {

    RecyclerView rcvTapTruyen;
    AutoCompleteTextView edtTimKiem;
    Button btnThem;
    List<TapTruyen> tapTruyenList;
    TapTruyenAdapter tapTruyenAdapter;
    TapTruyenDAO tapTruyenDAO;
    ArrayAdapter<TapTruyen> truyenArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tap_truyen);
        anhXa();
        onLoadTruyen();
        onClick();
    }

    private void anhXa() {
        rcvTapTruyen = findViewById(R.id.rcv_truyen);
        edtTimKiem = findViewById(R.id.edt_tim_kiem);
        btnThem = findViewById(R.id.btn_them);
        tapTruyenDAO = new TapTruyenDAO(QuanLyTapTruyenActivity.this);


        tapTruyenList = tapTruyenDAO.dsTapTruyen(Truyen.TRUYEN.getMaTruyen());
        tapTruyenAdapter = new TapTruyenAdapter(QuanLyTapTruyenActivity.this, tapTruyenList);
        rcvTapTruyen.setAdapter(tapTruyenAdapter);


        truyenArrayAdapter = new ArrayAdapter<>(QuanLyTapTruyenActivity.this, android.R.layout.simple_list_item_1, tapTruyenList);
        edtTimKiem.setAdapter(truyenArrayAdapter);
        Log.e("TAG", "anhXa: "+tapTruyenList.size() );
    }

    private void onLoadTruyen() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (tapTruyenList.size() != tapTruyenDAO.dsTapTruyen(Truyen.TRUYEN.getMaTruyen()).size()) {
                    tapTruyenList = tapTruyenDAO.dsTapTruyen(Truyen.TRUYEN.getMaTruyen());
                    tapTruyenAdapter = new TapTruyenAdapter(QuanLyTapTruyenActivity.this, tapTruyenList);
                    rcvTapTruyen.setAdapter(tapTruyenAdapter);
                }
                handler.postDelayed(this, 300);
            }
        };
        handler.post(runnable);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<TapTruyen> timKiem(String tuKhoa) {
        if (tuKhoa.equals("")) {
            return tapTruyenList;
        }
        return tapTruyenList.stream().filter(tapTruyen -> tapTruyen.getTenTap().contains(tuKhoa)).collect(Collectors.toList());

    }

    private void onClick() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ThemTapTruyenActivity.class));
            }
        });


        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tapTruyenAdapter = new TapTruyenAdapter(QuanLyTapTruyenActivity.this, timKiem(edtTimKiem.getText().toString()));
                rcvTapTruyen.setAdapter(tapTruyenAdapter);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tapTruyenAdapter = new TapTruyenAdapter(QuanLyTapTruyenActivity.this, timKiem(edtTimKiem.getText().toString()));
                rcvTapTruyen.setAdapter(tapTruyenAdapter);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable editable) {
                tapTruyenAdapter = new TapTruyenAdapter(QuanLyTapTruyenActivity.this, timKiem(edtTimKiem.getText().toString()));
                rcvTapTruyen.setAdapter(tapTruyenAdapter);
            }
        });
    }
}