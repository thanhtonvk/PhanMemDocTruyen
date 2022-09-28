package com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tuanh.phanmemdoctruyen.DAO.TheLoaiDAO;
import com.tuanh.phanmemdoctruyen.DAO.TruyenDAO;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThemTruyenActivity extends AppCompatActivity {


    EditText edtTenTruyen, edtTacGia, edtNamSangTac, edtMoTa, edtHinhAnh, edtTheLoai;
    RecyclerView rcvTheLoai;
    Button btnThem;
    TheLoaiMiniAdapter adapter;
    TheLoaiDAO theLoaiDAO;
    TruyenDAO truyenDAO;
    List<TheLoai> theLoaiList;
    int maTruyen;
    String tenTruyen, tacGia, namSangTac, moTa, hinhAnh;
    public static List<TheLoai> THELOAIS_TMP;
    public static String THELOAI_TMP = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_truyen);
        anhXa();
        onLoadTheLoai();
        onClick();
    }

    private void anhXa() {
        edtTenTruyen = findViewById(R.id.edt_ten_truyen);
        edtTacGia = findViewById(R.id.edt_tac_gia);
        edtNamSangTac = findViewById(R.id.edt_nam_sang_tac);
        edtMoTa = findViewById(R.id.edt_mo_ta);
        edtHinhAnh = findViewById(R.id.edt_hinh_anh);
        edtTheLoai = findViewById(R.id.edt_the_loai);
        rcvTheLoai = findViewById(R.id.rcv_the_loai);
        btnThem = findViewById(R.id.btn_them);
        theLoaiDAO = new TheLoaiDAO(ThemTruyenActivity.this);
        truyenDAO = new TruyenDAO(ThemTruyenActivity.this);
        theLoaiList = theLoaiDAO.dsLoai();
        adapter = new TheLoaiMiniAdapter(ThemTruyenActivity.this, theLoaiList);
        rcvTheLoai.setAdapter(adapter);
        maTruyen = new Random().nextInt(1000);
        THELOAIS_TMP = new ArrayList<>();
    }


    private void onLoadTheLoai() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                edtTheLoai.setText(THELOAI_TMP);
                handler.postDelayed(this, 300);
            }
        };
        handler.post(runnable);
    }

    private void onClick() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tenTruyen = edtTenTruyen.getText().toString();
                tacGia = edtTacGia.getText().toString();
                namSangTac = edtNamSangTac.getText().toString();
                moTa = edtMoTa.getText().toString();
                hinhAnh = edtHinhAnh.getText().toString();
                for (TheLoai theLoai : THELOAIS_TMP
                ) {
                    truyenDAO.themCTTruyen(maTruyen, theLoai.getMaLoai());
                }
                Truyen truyen = new Truyen(maTruyen, tenTruyen, tacGia, namSangTac, moTa, hinhAnh);
                if (truyenDAO.themTruyen(truyen) > 0) {
                    Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}