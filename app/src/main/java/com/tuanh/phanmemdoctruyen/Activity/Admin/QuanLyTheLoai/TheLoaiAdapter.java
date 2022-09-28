package com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTheLoai;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen.CapNhatTruyenActivity;
import com.tuanh.phanmemdoctruyen.DAO.TheLoaiDAO;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    Context context;
    List<TheLoai> theLoaiList;
    TheLoaiDAO theLoaiDAO;

    public TheLoaiAdapter(Context context, List<TheLoai> theLoaiList) {
        this.context = context;
        this.theLoaiList = theLoaiList;
        theLoaiDAO = new TheLoaiDAO(context);
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
        holder.tvTen.setText(theLoai.getTenLoai());
        Glide.with(context).load(theLoai.getHinhAnh()).error(R.drawable.ic_launcher_background).into(holder.imgHinhAnh);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog(theLoai);
            }
        });
    }


    private void dialog(TheLoai theLoai) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Tùy chọn");
        builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TheLoai.THELOAI = theLoai;
                context.startActivity(new Intent(context, CapNhatTheLoaiActivity.class));
            }
        });
        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                theLoaiDAO.xoaLoai(theLoai.getMaLoai());
                notifyDataSetChanged();
            }
        });
        builder.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return theLoaiList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhAnh;
        TextView tvTen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAnh = itemView.findViewById(R.id.img_hinh_anh);
            tvTen = itemView.findViewById(R.id.tv_ten_the_loai);
        }
    }
}
