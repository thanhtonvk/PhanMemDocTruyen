package com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;

import static com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen.ThemTruyenActivity.*;

public class TheLoaiMiniAdapter extends RecyclerView.Adapter<TheLoaiMiniAdapter.ViewHolder> {
    Context context;
    List<TheLoai> theLoaiList;

    public TheLoaiMiniAdapter(Context context, List<TheLoai> theLoaiList) {
        this.context = context;
        this.theLoaiList = theLoaiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_the_loai_mini, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiList.get(position);
        holder.tvTenTheLoai.setText(theLoai.getTenLoai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                THELOAIS_TMP.add(theLoai);
                THELOAI_TMP += theLoai.getTenLoai() + " ; ";
            }
        });
    }

    @Override
    public int getItemCount() {
        return theLoaiList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenTheLoai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTheLoai = itemView.findViewById(R.id.tv_ten_the_loai);
        }
    }
}
