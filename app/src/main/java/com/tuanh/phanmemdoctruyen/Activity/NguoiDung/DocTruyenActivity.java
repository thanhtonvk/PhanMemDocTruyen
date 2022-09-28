package com.tuanh.phanmemdoctruyen.Activity.NguoiDung;

import static com.tuanh.phanmemdoctruyen.Models.TapTruyen.TAPHIENTAI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;

public class DocTruyenActivity extends AppCompatActivity {

    List<TapTruyen> truyenList;
    TextView tv_noi_dung;
    Button btnTruoc, btnSau;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        anhXa();
        truyenList = Truyen.TRUYEN.getTapTruyenList();
        loadTruyen(TAPHIENTAI);
        onClick();
    }


    private void onClick() {
        btnTruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                TAPHIENTAI--;
                if (TAPHIENTAI < 0) {
                    TAPHIENTAI++;
                }
                loadTruyen(TAPHIENTAI);

            }
        });
        btnSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                TAPHIENTAI++;
                if (TAPHIENTAI > truyenList.size() - 1) {
                    TAPHIENTAI--;
                }
                loadTruyen(TAPHIENTAI);

            }
        });
    }

    private void anhXa() {
        tv_noi_dung = findViewById(R.id.tv_noi_dung);
        btnTruoc = findViewById(R.id.btn_truoc);
        btnSau = findViewById(R.id.btn_sau);
        scrollView = findViewById(R.id.scroll_view);
    }

    private void loadTruyen(int idx) {
        TapTruyen tapTruyen = truyenList.get(idx);
        setTitle(tapTruyen.getTenTap());
        tv_noi_dung.setText(tapTruyen.getNoiDung());
    }
}