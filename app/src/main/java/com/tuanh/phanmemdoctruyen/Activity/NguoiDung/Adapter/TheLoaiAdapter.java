package com.tuanh.phanmemdoctruyen.Activity.NguoiDung.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tuanh.phanmemdoctruyen.Activity.NguoiDung.DanhSachTruyenActivity;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    Context context;
    List<TheLoai> theLoaiList;

    public TheLoaiAdapter(Context context, List<TheLoai> theLoaiList) {
        this.context = context;
        this.theLoaiList = theLoaiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_the_loai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiList.get(position);
        holder.tvTenLoai.setText(theLoai.getTenLoai());
        Glide.with(context).load(theLoai.getHinhAnh()).error(R.drawable.ic_launcher_background).into(holder.imgHinhAnh);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoai.THELOAI = theLoai;
                context.startActivity(new Intent(context, DanhSachTruyenActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return theLoaiList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLoai;
        ImageView imgHinhAnh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoai = itemView.findViewById(R.id.tv_ten_the_loai);
            imgHinhAnh = itemView.findViewById(R.id.img_hinh_anh);
        }
    }
}
