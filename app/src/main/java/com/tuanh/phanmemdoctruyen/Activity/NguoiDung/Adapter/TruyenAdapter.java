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
import com.tuanh.phanmemdoctruyen.Activity.NguoiDung.ChiTietTruyenActivity;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;
import static com.tuanh.phanmemdoctruyen.Models.Truyen.TRUYEN;
public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.ViewHolder> {
    Context context;
    List<Truyen> truyenList;

    public TruyenAdapter(Context context, List<Truyen> truyenList) {
        this.context = context;
        this.truyenList = truyenList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_truyen, parent, false);
        return new TruyenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truyen truyen = truyenList.get(position);
        holder.tvTacGia.setText("Tác giả: " + truyen.getTacGia());
        holder.tvTenTruyen.setText(truyen.getTenTruyen());
        holder.tvSotap.setText("Số tập: " + truyen.getTapTruyenList().size());
        Glide.with(context).load(truyen.getHinhAnh()).error(R.drawable.ic_launcher_background).into(holder.imgHinhAnh);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TRUYEN = truyen;
                context.startActivity(new Intent(context, ChiTietTruyenActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return truyenList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhAnh;
        TextView tvTenTruyen, tvSotap, tvTacGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAnh = itemView.findViewById(R.id.img_hinh_anh);
            tvTenTruyen = itemView.findViewById(R.id.tv_ten_truyen);
            tvSotap = itemView.findViewById(R.id.tv_so_tap);
            tvTacGia = itemView.findViewById(R.id.tv_tac_gia);
        }
    }
}
