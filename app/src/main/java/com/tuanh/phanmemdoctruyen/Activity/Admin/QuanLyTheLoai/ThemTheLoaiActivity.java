package com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTheLoai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tuanh.phanmemdoctruyen.DAO.TheLoaiDAO;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.R;

public class ThemTheLoaiActivity extends AppCompatActivity {

    Button btnThem;
    EditText edtTen, edtHinhAnh;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_the_loai);
        anhXa();
        onClick();
    }

    private void anhXa() {
        btnThem = findViewById(R.id.btn_them);
        edtTen = findViewById(R.id.edt_ten_the_loai);
        edtHinhAnh = findViewById(R.id.edt_hinh_anh);
        theLoaiDAO = new TheLoaiDAO(ThemTheLoaiActivity.this);
    }

    private void onClick() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoai theLoai = new TheLoai(edtTen.getText().toString(), edtHinhAnh.getText().toString());
                if (theLoaiDAO.themLoai(theLoai) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}