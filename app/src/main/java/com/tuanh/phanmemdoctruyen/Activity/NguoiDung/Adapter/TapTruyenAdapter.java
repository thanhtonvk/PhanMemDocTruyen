package com.tuanh.phanmemdoctruyen.Activity.NguoiDung.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tuanh.phanmemdoctruyen.Activity.NguoiDung.DocTruyenActivity;
import com.tuanh.phanmemdoctruyen.DAO.TapTruyenDAO;
import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.R;

import java.util.List;

public class TapTruyenAdapter extends RecyclerView.Adapter<TapTruyenAdapter.ViewHolder> {
    Context context;
    List<TapTruyen> tapTruyenList;
    TapTruyenDAO tapTruyenDAO;

    public TapTruyenAdapter(Context context, List<TapTruyen> tapTruyenList) {
        this.context = context;
        this.tapTruyenList = tapTruyenList;
        tapTruyenDAO = new TapTruyenDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_tap_truyen, parent, false);
        return new TapTruyenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TapTruyen tapTruyen = tapTruyenList.get(position);
        holder.tvTenTap.setText("Tập " + (position + 1) + ": " + tapTruyen.getTenTap());
        if (tapTruyen.getNoiDung().length() <= 200) {
            holder.tvTomTat.setText(tapTruyen.getNoiDung());
        } else {
            holder.tvTomTat.setText(tapTruyen.getNoiDung().substring(0, 200) + " ...");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TapTruyen.TAPHIENTAI = holder.getAdapterPosition();
                context.startActivity(new Intent(context, DocTruyenActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return tapTruyenList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenTap, tvTomTat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTap = itemView.findViewById(R.id.tv_ten_tap);
            tvTomTat = itemView.findViewById(R.id.tv_tom_tat);
        }
    }
}
