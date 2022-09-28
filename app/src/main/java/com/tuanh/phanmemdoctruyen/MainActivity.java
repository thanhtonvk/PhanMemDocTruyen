package com.tuanh.phanmemdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tuanh.phanmemdoctruyen.Activity.Admin.TrangQuanTriActivity;
import com.tuanh.phanmemdoctruyen.Activity.NguoiDung.TrangChuActivity;
import com.tuanh.phanmemdoctruyen.DAO.TaiKhoanDAO;
import com.tuanh.phanmemdoctruyen.Models.TaiKhoan;

public class MainActivity extends AppCompatActivity {


    Button btnDangNhap, btnDangKy;
    EditText edtTaiKhoan, edtMatKhau;
    TaiKhoanDAO taiKhoanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        onClick();
    }


    private void onClick() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenTK = edtTaiKhoan.getText().toString();
                String matKhau = edtMatKhau.getText().toString();

                if (tenTK.equals("admin") && matKhau.equals("admin")) {
                    //trang quản trị
                    startActivity(new Intent(getApplicationContext(), TrangQuanTriActivity.class));
                } else {
                    TaiKhoan taiKhoan = taiKhoanDAO.dangNhap(tenTK, matKhau);
                    if (taiKhoan != null) {
                        TaiKhoan.TAI_KHOAN = taiKhoan;
                        startActivity(new Intent(getApplicationContext(), TrangChuActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DangKyActivity.class));
            }
        });
    }


    private void anhXa() {
        taiKhoanDAO = new TaiKhoanDAO(MainActivity.this);
        btnDangKy = findViewById(R.id.btn_dang_ky);
        btnDangNhap = findViewById(R.id.btn_dang_nhap);
        edtTaiKhoan = findViewById(R.id.edt_taikhoan);
        edtMatKhau = findViewById(R.id.edt_mat_khau);
    }
}