package com.tuanh.phanmemdoctruyen.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.SQLiteHelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class TapTruyenDAO {
    DBHelper dbHelper;

    public TapTruyenDAO(Context context) {
        dbHelper = new DBHelper(context);
    }


    public static String TABLE_TAPTRUYEN = "create table TapTruyen(" +
            "maTap int primary key autoincrement," +
            "tenTap ntext," +
            "noiDung ntext," +
            "maTruyen int not null)";

    public int themTapTruyen(TapTruyen tapTruyen, int maTruyen) {
        ContentValues values = new ContentValues();
        values.put("tenTap", tapTruyen.getTenTap());
        values.put("noiDung", tapTruyen.getNoiDung());
        values.put("maTruyen", maTruyen);
        return dbHelper.them("TapTruyen", values);
    }

    public int suaTapTruyen(TapTruyen tapTruyen, int maTruyen) {
        ContentValues values = new ContentValues();
        values.put("maTap", tapTruyen.getMaTap());
        values.put("tenTap", tapTruyen.getTenTap());
        values.put("noiDung", tapTruyen.getNoiDung());
        values.put("maTruyen", maTruyen);
        return dbHelper.sua("TapTruyen", values, "maTap = ?", new String[]{String.valueOf(tapTruyen.getMaTap())});
    }

    public int xoaTapTruyen(int maTap) {
        return dbHelper.xoa("TapTruyen", "maTap = ?", new String[]{String.valueOf(maTap)});
    }

    public List<TapTruyen> dsTapTruyen(int maTruyen) {
        String query = String.format("select * from TapTruyen where maTruyen = %s", maTruyen);
        Cursor cursor = dbHelper.rawQuery(query);
        List<TapTruyen> tapTruyenList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TapTruyen tapTruyen = new TapTruyen(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            tapTruyenList.add(tapTruyen);
            cursor.moveToNext();
        }
        cursor.close();
        return tapTruyenList;
    }
}
