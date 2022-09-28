package com.tuanh.phanmemdoctruyen.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.Models.ThuVien;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.SQLiteHelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ThuVienDAO {
    DBHelper dbHelper;
    public static String TABLE_THUVIEN = "create table ThuVien(" +
            "maThuVien int primary key autoincrement," +
            "tenTaiKhoan varchar(50) not null," +
            "maTruyen int not null)";

    public ThuVienDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int themThuVien(int maTruyen, String taiKhoan) {
        ContentValues values = new ContentValues();
        values.put("tenTaiKhoan", taiKhoan);
        values.put("maTruyen", maTruyen);
        return dbHelper.them("ThuVien", values);
    }

    public int xoaThuVien(int mathuVien) {
        return dbHelper.xoa("ThuVien", "maThuVien = ?", new String[]{String.valueOf(mathuVien)});
    }

    public List<TheLoai> dsTheLoaiCuaTruyen(int maTruyen) {
        String query = String.format("select TheLoai.maLoai,tenLoai,TheLoai.hinhAnh from TheLoai,CTTruyen where TheLoai.maLoai = CTTruyen.maLoai and %s = CTTruyen.maTruyen", maTruyen);
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

    public List<TapTruyen> dsTapTruyen(int maTruyen) {
        String query = String.format("select maTap,tenTap,noiDung from TapTruyen where maTruyen = %s", maTruyen);
        Cursor cursor = dbHelper.rawQuery(query);
        List<TapTruyen> tapTruyenList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TapTruyen tapTruyen = new TapTruyen(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            tapTruyenList.add(tapTruyen);
            cursor.moveToNext();
        }
        return tapTruyenList;
    }

    public List<ThuVien> dsThuVien(String tenTaiKhoan) {
        List<ThuVien> thuVienList = new ArrayList<>();
        String query = String.format("select maThuVien,maTruyen,tenTruyen,tacGia,namSangTac,moTa,hinhAnh  from ThuVien,Truyen where ThuVien.maTruyen = Truyen.maTruyen and tenTaiKhoan = '%s'", tenTaiKhoan);
        Cursor cursor = dbHelper.rawQuery(query);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ThuVien thuVien = new ThuVien();
            thuVien.setMaThuVien(cursor.getInt(0));
            Truyen truyen = new Truyen(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            truyen.setTheLoaiList(dsTheLoaiCuaTruyen(truyen.getMaTruyen()));
            truyen.setTapTruyenList(dsTapTruyen(truyen.getMaTruyen()));
            cursor.moveToNext();
        }
        return thuVienList;
    }
}
