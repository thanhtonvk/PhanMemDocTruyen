package com.tuanh.phanmemdoctruyen.Activity.NguoiDung;

import static com.tuanh.phanmemdoctruyen.Models.TheLoai.THELOAI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.tuanh.phanmemdoctruyen.Activity.NguoiDung.Adapter.TruyenAdapter;
import com.tuanh.phanmemdoctruyen.DAO.TruyenDAO;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;

public class DanhSachTruyenActivity extends AppCompatActivity {

    RecyclerView rcvTruyen;
    List<Truyen> truyenList;
    TruyenAdapter truyenAdapter;
    TruyenDAO truyenDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_truyen);
        setTitle(THELOAI.getTenLoai());
        truyenDAO = new TruyenDAO(this);
        rcvTruyen = findViewById(R.id.rcv_truyen);
        rcvTruyen.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        truyenList = truyenDAO.dsTruyenTheoLoai(THELOAI.getMaLoai());
        truyenAdapter = new TruyenAdapter(this, truyenList);
        rcvTruyen.setAdapter(truyenAdapter);
    }
}