package com.tuanh.phanmemdoctruyen.Activity.NguoiDung;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.tuanh.phanmemdoctruyen.Activity.NguoiDung.Adapter.TheLoaiAdapter;
import com.tuanh.phanmemdoctruyen.Activity.NguoiDung.Adapter.TruyenAdapter;
import com.tuanh.phanmemdoctruyen.DAO.TheLoaiDAO;
import com.tuanh.phanmemdoctruyen.DAO.TruyenDAO;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;
import java.util.stream.Collectors;

public class TrangChuActivity extends AppCompatActivity {

    AutoCompleteTextView edtTimKiem;
    Button btnYeuThich;
    RecyclerView rcvTheLoai;
    RecyclerView rcvTruyen;
    List<TheLoai> theLoaiList;
    TheLoaiAdapter theLoaiAdapter;
    TheLoaiDAO theLoaiDAO;


    List<Truyen> truyenList;
    TruyenAdapter truyenAdapter;
    TruyenDAO truyenDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        anhXa();
        onClick();
    }
    private void onClick(){
        btnYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ThuVienActivity.class));
            }
        });
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                truyenAdapter = new TruyenAdapter(TrangChuActivity.this,timKiem(edtTimKiem.getText().toString()));
                rcvTruyen.setAdapter(truyenAdapter);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                truyenAdapter = new TruyenAdapter(TrangChuActivity.this,timKiem(edtTimKiem.getText().toString()));
                rcvTruyen.setAdapter(truyenAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                truyenAdapter = new TruyenAdapter(TrangChuActivity.this,timKiem(edtTimKiem.getText().toString()));
                rcvTruyen.setAdapter(truyenAdapter);
            }
        });
    }

    private void anhXa() {
        theLoaiDAO = new TheLoaiDAO(TrangChuActivity.this);
        edtTimKiem = findViewById(R.id.edt_tim_kiem);
        btnYeuThich = findViewById(R.id.btn_yeu_thich);
        rcvTheLoai = findViewById(R.id.rcv_the_loai);

        rcvTruyen = findViewById(R.id.rcv_truyen);
        rcvTruyen.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        theLoaiList = theLoaiDAO.dsLoai();
        theLoaiAdapter = new TheLoaiAdapter(this, theLoaiList);
        rcvTheLoai.setAdapter(theLoaiAdapter);
        truyenDAO = new TruyenDAO(this);
        truyenList = truyenDAO.dsTruyen();
        truyenAdapter = new TruyenAdapter(this, truyenList);
        rcvTruyen.setAdapter(truyenAdapter);
    }
    private List<Truyen> timKiem(String tuKhoa) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return truyenList.stream().filter(theLoai -> theLoai.getTenTruyen().toLowerCase().contains(tuKhoa.toLowerCase())).collect(Collectors.toList());
        }
        return truyenList;
    }
}