package com.tuanh.phanmemdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tuanh.phanmemdoctruyen.DAO.TaiKhoanDAO;
import com.tuanh.phanmemdoctruyen.Models.TaiKhoan;

public class DangKyActivity extends AppCompatActivity {

    EditText edtTaiKhoan, edtMatKhau, edtEmail, edtSdt, edtHoTen;
    Button btnDangKy;
    TaiKhoanDAO taiKhoanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        anhXa();
        onClick();
    }

    private void onClick() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaiKhoan taiKhoan = new TaiKhoan(edtTaiKhoan.getText().toString(), edtMatKhau.getText().toString(), edtSdt.getText().toString(), edtEmail.getText().toString(), edtHoTen.getText().toString());
                if (taiKhoanDAO.dangKy(taiKhoan) > 0) {
                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DangKyActivity.this, "Đăng ký tài khoản thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void anhXa() {
        edtTaiKhoan = findViewById(R.id.edt_taikhoan);
        edtMatKhau = findViewById(R.id.edt_mat_khau);
        edtEmail = findViewById(R.id.edt_email);
        edtSdt = findViewById(R.id.edt_sdt);
        edtHoTen = findViewById(R.id.edt_ho_ten);
        btnDangKy = findViewById(R.id.btn_dang_ky);
        taiKhoanDAO = new TaiKhoanDAO(DangKyActivity.this);
    }
}