package com.tuanh.phanmemdoctruyen.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.Models.ThuVien;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.SQLiteHelper.DBHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

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
        cursor.close();
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
        cursor.close();
        return tapTruyenList;
    }

    public List<ThuVien> dsThuVien(String tenTaiKhoan) {
        List<ThuVien> thuVienList = new ArrayList<>();
        String query = String.format("select maThuVien,ThuVien.maTruyen,tenTruyen,tacGia,namSangTac,moTa,hinhAnh  from ThuVien,Truyen where ThuVien.maTruyen = Truyen.maTruyen and tenTaiKhoan = '%s'", tenTaiKhoan);
        Log.e("TAG", "dsThuVien: " + query);
        Cursor cursor = dbHelper.rawQuery(query);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.e("TAG", "Run");
            ThuVien thuVien = new ThuVien();
            thuVien.setMaThuVien(cursor.getInt(0));
            Truyen truyen = new Truyen(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            truyen.setTheLoaiList(dsTheLoaiCuaTruyen(truyen.getMaTruyen()));
            truyen.setTapTruyenList(dsTapTruyen(truyen.getMaTruyen()));
            thuVien.setTruyen(truyen);
            thuVienList.add(thuVien);
            cursor.moveToNext();
        }
        cursor.close();

        return thuVienList;
    }

    public List<Truyen> getTruyen(String tenTaiKhoan) {
        List<ThuVien> thuVienList = dsThuVien(tenTaiKhoan);
        Log.e("TAG", "getTruyen: " + thuVienList.size());
        List<Truyen> truyenList = new ArrayList<>();
        for (ThuVien thuVien : thuVienList) {
            truyenList.add(thuVien.getTruyen());
        }
        SortedSet<Truyen> set = new TreeSet<Truyen>(new Comparator<Truyen>() {

            public int compare(Truyen o1, Truyen o2) {
                if (o1.getMaTruyen() == o2.getMaTruyen()) return 1;
                else return 0;
            }
        });
        set.addAll(truyenList);
        return truyenList;
    }

}
