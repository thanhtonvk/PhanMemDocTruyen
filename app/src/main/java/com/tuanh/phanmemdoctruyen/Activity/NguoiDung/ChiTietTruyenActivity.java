package com.tuanh.phanmemdoctruyen.Activity.NguoiDung;

import static com.tuanh.phanmemdoctruyen.Models.Truyen.TRUYEN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tuanh.phanmemdoctruyen.Activity.NguoiDung.Adapter.TapTruyenAdapter;
import com.tuanh.phanmemdoctruyen.DAO.ThuVienDAO;
import com.tuanh.phanmemdoctruyen.Models.TaiKhoan;
import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;

public class ChiTietTruyenActivity extends AppCompatActivity {

    ImageView imgHinhAnh;
    TextView tvTenTruyen, tvTacGia, tvTheLoai, tvGioiThieu;
    RecyclerView rcvTap;
    Button btnThemYeuThich, btnDocTruyen;
    List<TapTruyen> tapTruyenList;
    TapTruyenAdapter tapTruyenAdapter;
    ThuVienDAO thuVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_truyen);
        anhXa();
        load();
        onClick();

    }

    private void anhXa() {
        imgHinhAnh = findViewById(R.id.img_hinh_anh);
        tvTenTruyen = findViewById(R.id.tv_ten_truyen);
        tvTacGia = findViewById(R.id.tv_tac_gia);
        tvTheLoai = findViewById(R.id.tv_ten_the_loai);
        tvGioiThieu = findViewById(R.id.tv_tom_tat);
        rcvTap = findViewById(R.id.rcv_tap);
        btnThemYeuThich = findViewById(R.id.btn_yeu_thich);
        btnDocTruyen = findViewById(R.id.btn_doc_truyen);
        thuVienDAO = new ThuVienDAO(this);
    }

    private void onClick() {
        btnDocTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TapTruyen.TAPHIENTAI = 0;
                startActivity(new Intent(getApplicationContext(), DocTruyenActivity.class));
            }
        });
        btnThemYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rs = thuVienDAO.themThuVien(TRUYEN.getMaTruyen(), TaiKhoan.TAI_KHOAN.getTenTaiKhoan());
                if (rs > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void load() {
        if (TRUYEN != null) {
            Glide.with(ChiTietTruyenActivity.this).load(TRUYEN.getHinhAnh()).error(R.drawable.ic_launcher_background).into(imgHinhAnh);
            tvTenTruyen.setText(TRUYEN.getTenTruyen());
            tvTacGia.setText("Tác giả: " + TRUYEN.getTacGia());
            String theLoai = "";
            for (TheLoai x : TRUYEN.getTheLoaiList()
            ) {
                theLoai += x.getTenLoai() + " ";
            }
            tvTheLoai.setText("Thể loại: " + theLoai);
            tvGioiThieu.setText("Giới thiệu: " + TRUYEN.getMoTa());


            tapTruyenList = TRUYEN.getTapTruyenList();

            tapTruyenAdapter = new TapTruyenAdapter(this, tapTruyenList);
            rcvTap.setAdapter(tapTruyenAdapter);
            setTitle(TRUYEN.getTenTruyen());
        }


    }
}