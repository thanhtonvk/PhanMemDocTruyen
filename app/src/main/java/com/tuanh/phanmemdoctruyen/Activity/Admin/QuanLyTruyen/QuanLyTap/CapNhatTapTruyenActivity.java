package com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen.QuanLyTap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tuanh.phanmemdoctruyen.DAO.TapTruyenDAO;
import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

public class CapNhatTapTruyenActivity extends AppCompatActivity {
    EditText edtTenTap, edtNoiDung;
    Button btnThem;
    TapTruyenDAO tapTruyenDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_tap_truyen);
        anhXa();
        onClick();
        onLoad();
    }

    private void anhXa() {
        edtTenTap = findViewById(R.id.edt_ten_tap);
        edtNoiDung = findViewById(R.id.edt_noi_dung);
        btnThem = findViewById(R.id.btn_them);
        tapTruyenDAO = new TapTruyenDAO(CapNhatTapTruyenActivity.this);
    }

    private void onClick() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TapTruyen truyen = new TapTruyen(edtTenTap.getText().toString(), edtNoiDung.getText().toString());
                truyen.setMaTap(TapTruyen.TAP_TRUYEN.getMaTap());
                if (tapTruyenDAO.suaTapTruyen(truyen, Truyen.TRUYEN.getMaTruyen()) > 0) {
                    Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onLoad() {
        if (TapTruyen.TAP_TRUYEN != null) {
            edtTenTap.setText(TapTruyen.TAP_TRUYEN.getTenTap());
            edtNoiDung.setText(TapTruyen.TAP_TRUYEN.getNoiDung());
        }
    }
}