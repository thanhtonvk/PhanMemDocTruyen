package com.tuanh.phanmemdoctruyen.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.SQLiteHelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    DBHelper dbHelper;

    public TheLoaiDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int themLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", theLoai.getTenLoai());
        values.put("hinhAnh", theLoai.getHinhAnh());
        return dbHelper.them("TheLoai", values);
    }

    public int suaLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("maLoai",theLoai.getMaLoai());
        values.put("tenLoai", theLoai.getTenLoai());
        values.put("hinhAnh", theLoai.getHinhAnh());
        return dbHelper.sua("TheLoai", values, "maLoai = ?", new String[]{String.valueOf(theLoai.getMaLoai())});
    }

    public int xoaLoai(int maLoai) {
        return dbHelper.xoa("TheLoai", "maLoai = ?", new String[]{String.valueOf(maLoai)});
    }

    public List<TheLoai> dsLoai() {
        String query = "select * from TheLoai";
        Cursor cursor = dbHelper.rawQuery(query);
        List<TheLoai> theLoaiList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TheLoai theLoai = new TheLoai(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            theLoaiList.add(theLoai);
            cursor.moveToNext();
        }
        return theLoaiList;
    }

}
