package com.tuanh.phanmemdoctruyen.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.Models.Truyen;
import com.tuanh.phanmemdoctruyen.SQLiteHelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class TruyenDAO {
    DBHelper dbHelper;

    public TruyenDAO(Context context) {
        dbHelper = new DBHelper(context);
    }


    public static String TABLE_TRUYEN = "create table Truyen(" +
            "maTruyen integer primary key AUTOINCREMENT," +
            "tenTruyen ntext," +
            "tacGia ntext," +
            "namSangTac integer," +
            "moTa ntext," +
            "hinhAnh ntext)";

    public int themCTTruyen(int maTruyen, int maLoai) {
        ContentValues values = new ContentValues();
        values.put("maTruyen", maTruyen);
        values.put("maLoai", maLoai);
        return dbHelper.them("CTTruyen", values);
    }

    public int xoaCTTruyen(int maCTTruyen) {
        return dbHelper.xoa("CTTruyen", "maCTTruyen = ?", new String[]{String.valueOf(maCTTruyen)});
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


    public List<Truyen> dsTruyenTheoLoai(int maLoai) {
        String query = "select Truyen.maTruyen,tenTruyen,tacGia,namSangTac, moTa,hinhAnh from Truyen,CTTruyen where Truyen.maTruyen = CTTruyen.maTruyen and CTTruyen.maLoai = " + maLoai;
        Cursor cursor = dbHelper.rawQuery(query);
        cursor.moveToFirst();
        List<Truyen> truyenList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            Truyen truyen = new Truyen(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            truyen.setTheLoaiList(dsTheLoaiCuaTruyen(truyen.getMaTruyen()));
            truyen.setTapTruyenList(dsTapTruyen(truyen.getMaTruyen()));
            truyenList.add(truyen);
            cursor.moveToNext();
        }
        cursor.close();
        return truyenList;
    }


    public List<Truyen> dsTruyen() {
        List<Truyen> truyenList = new ArrayList<>();
        String query = "select * from Truyen";
        Cursor cursor = dbHelper.rawQuery(query);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Truyen truyen = new Truyen(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            truyen.setTheLoaiList(dsTheLoaiCuaTruyen(truyen.getMaTruyen()));
            truyen.setTapTruyenList(dsTapTruyen(truyen.getMaTruyen()));
            truyenList.add(truyen);
            cursor.moveToNext();
        }
        cursor.close();
        return truyenList;
    }

    public int themTruyen(Truyen truyen) {
        ContentValues values = new ContentValues();
        values.put("maTruyen", truyen.getMaTruyen());
        values.put("tenTruyen", truyen.getTenTruyen());
        values.put("tacGia", truyen.getTacGia());
        values.put("namSangTac", truyen.getNamSangTac());
        values.put("moTa", truyen.getMoTa());
        values.put("hinhAnh", truyen.getHinhAnh());
        return dbHelper.them("Truyen", values);
    }

    public int suaTruyen(Truyen truyen) {
        ContentValues values = new ContentValues();
        values.put("maTruyen", truyen.getMaTruyen());
        values.put("tenTruyen", truyen.getTenTruyen());
        values.put("tacGia", truyen.getTacGia());
        values.put("namSangTac", truyen.getNamSangTac());
        values.put("moTa", truyen.getMoTa());
        values.put("hinhAnh", truyen.getHinhAnh());
        return dbHelper.sua("Truyen", values, "maTruyen = ?", new String[]{String.valueOf(truyen.getMaTruyen())});
    }

    public int xoaTruyen(int maTruyen) {
        return dbHelper.xoa("Truyen", "maTruyen = ?", new String[]{String.valueOf(maTruyen)});
    }

}
