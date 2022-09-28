package com.tuanh.phanmemdoctruyen.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tuanh.phanmemdoctruyen.Models.TaiKhoan;
import com.tuanh.phanmemdoctruyen.SQLiteHelper.DBHelper;

public class TaiKhoanDAO {
    DBHelper dbHelper;

    public TaiKhoanDAO(Context context) {
        dbHelper = new DBHelper(context);
    }


    public int dangKy(TaiKhoan taiKhoan) {
        ContentValues values = new ContentValues();
        values.put("tenTaiKhoan", taiKhoan.getTenTaiKhoan());
        values.put("matKhau", taiKhoan.getMatKhau());
        values.put("sdt", taiKhoan.getSdt());
        values.put("email", taiKhoan.getEmail());
        values.put("hoTen", taiKhoan.getHoTen());
        return dbHelper.them("TaiKhoan", values);
    }

    public TaiKhoan dangNhap(String taiKhoan, String matKhau) {
        String query = String.format("select * from TaiKhoan where tenTaiKhoan = '%s' and matKhau = '%s'", taiKhoan, matKhau);
        Cursor cursor = dbHelper.rawQuery(query);
        TaiKhoan result = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result = new TaiKhoan(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
}
