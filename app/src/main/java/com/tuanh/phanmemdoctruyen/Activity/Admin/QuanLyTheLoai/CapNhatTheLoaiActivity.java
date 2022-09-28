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

import static com.tuanh.phanmemdoctruyen.Models.TheLoai.THELOAI;

public class CapNhatTheLoaiActivity extends AppCompatActivity {

    Button btnThem;
    EditText edtTen, edtHinhAnh;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_the_loai);
        anhXa();
        onClick();
        onLoad();
    }

    private void anhXa() {
        btnThem = findViewById(R.id.btn_them);
        edtTen = findViewById(R.id.edt_ten_the_loai);
        edtHinhAnh = findViewById(R.id.edt_hinh_anh);
        theLoaiDAO = new TheLoaiDAO(CapNhatTheLoaiActivity.this);
    }

    private void onLoad() {
        if (THELOAI != null) {
            edtTen.setText(THELOAI.getTenLoai());
            edtHinhAnh.setText(THELOAI.getHinhAnh());
        }
    }

    private void onClick() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoai theLoai = new TheLoai(THELOAI.getMaLoai(), edtTen.getText().toString(), edtHinhAnh.getText().toString());
                if (theLoaiDAO.suaLoai(theLoai) > 0) {
                    Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cập nhật không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}