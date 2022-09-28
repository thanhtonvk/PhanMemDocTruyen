package com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen;

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
import com.tuanh.phanmemdoctruyen.Activity.Admin.QuanLyTruyen.QuanLyTap.QuanLyTapTruyenActivity;
import com.tuanh.phanmemdoctruyen.DAO.TruyenDAO;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.ViewHolder> {

    List<Truyen> truyenList;
    Context context;
    TruyenDAO truyenDAO;

    public TruyenAdapter(List<Truyen> truyenList, Context context) {
        this.truyenList = truyenList;
        this.context = context;
        truyenDAO = new TruyenDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_truyen, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truyen truyen = truyenList.get(position);
        holder.tvSoTap.setText("Số tập " + truyen.getTapTruyenList().size());
        holder.tvTenTruyen.setText(truyen.getTenTruyen());
        holder.tvTacGia.setText("Tác giả: " + truyen.getTacGia());
        Glide.with(context).load(truyen.getHinhAnh()).error(R.drawable.ic_launcher_background).into(holder.imgHinhAnh);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog(truyen);
                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mở ra danh sách các tập truyện
                Truyen.TRUYEN = truyen;
                context.startActivity(new Intent(context, QuanLyTapTruyenActivity.class));
            }
        });
    }


    private void dialog(Truyen truyen) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Tùy chọn");
        builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Truyen.TRUYEN = truyen;
                context.startActivity(new Intent(context, CapNhatTruyenActivity.class));
            }
        });
        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                truyenDAO.xoaTruyen(truyen.getMaTruyen());
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
        return truyenList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhAnh;
        TextView tvTenTruyen, tvSoTap, tvTacGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAnh = itemView.findViewById(R.id.img_hinh_anh);
            tvTacGia = itemView.findViewById(R.id.tv_tac_gia);
            tvTenTruyen = itemView.findViewById(R.id.tv_ten_truyen);
            tvSoTap = itemView.findViewById(R.id.tv_so_tap);
        }
    }
}
