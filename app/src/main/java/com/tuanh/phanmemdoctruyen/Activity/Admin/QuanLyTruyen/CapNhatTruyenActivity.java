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

import static com.tuanh.phanmemdoctruyen.Models.Truyen.TRUYEN;

public class CapNhatTruyenActivity extends AppCompatActivity {

    EditText edtTenTruyen, edtTacGia, edtNamSangTac, edtMoTa, edtHinhAnh, edtTheLoai;
    RecyclerView rcvTheLoai;
    Button btnThem;
    TruyenDAO truyenDAO;
    String tenTruyen, tacGia, namSangTac, moTa, hinhAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_truyen);
        anhXa();
        onLoad();
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
        truyenDAO = new TruyenDAO(CapNhatTruyenActivity.this);
    }

    private void onLoad() {
        if (TRUYEN != null) {
            edtTenTruyen.setText(TRUYEN.getTenTruyen());
            edtTacGia.setText(TRUYEN.getTacGia());
            edtNamSangTac.setText(TRUYEN.getNamSangTac());
            edtMoTa.setText(TRUYEN.getMoTa());
            edtHinhAnh.setText(TRUYEN.getHinhAnh());
        }
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
                Truyen truyen = new Truyen(TRUYEN.getMaTruyen(), tenTruyen, tacGia, namSangTac, moTa, hinhAnh);
                if (truyenDAO.suaTruyen(truyen) > 0) {
                    Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}