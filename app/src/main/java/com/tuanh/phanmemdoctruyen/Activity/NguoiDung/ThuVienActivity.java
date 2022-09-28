package com.tuanh.phanmemdoctruyen.Activity.NguoiDung;

import static com.tuanh.phanmemdoctruyen.Models.TheLoai.THELOAI;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.tuanh.phanmemdoctruyen.Activity.NguoiDung.Adapter.TruyenAdapter;
import com.tuanh.phanmemdoctruyen.DAO.ThuVienDAO;
import com.tuanh.phanmemdoctruyen.Models.TaiKhoan;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;

public class ThuVienActivity extends AppCompatActivity {
    RecyclerView rcvTruyen;
    List<Truyen> truyenList;
    TruyenAdapter truyenAdapter;
    ThuVienDAO thuVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_vien);
        setTitle("Thư viện");
        thuVienDAO = new ThuVienDAO(this);
        rcvTruyen = findViewById(R.id.rcv_truyen);
        rcvTruyen.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        truyenList = thuVienDAO.getTruyen(TaiKhoan.TAI_KHOAN.getTenTaiKhoan());
        Log.e("TAG", "onCreate: "+truyenList.size() );
        truyenAdapter = new TruyenAdapter(this, truyenList);
        rcvTruyen.setAdapter(truyenAdapter);
    }


}