package com.tuanh.phanmemdoctruyen.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tuanh.phanmemdoctruyen.Models.TaiKhoan;
import com.tuanh.phanmemdoctruyen.Models.TapTruyen;
import com.tuanh.phanmemdoctruyen.Models.TheLoai;
import com.tuanh.phanmemdoctruyen.Models.ThuVien;
import com.tuanh.phanmemdoctruyen.Models.Truyen;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "database.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TaiKhoan.TABLE_TAIKHOAN);
        sqLiteDatabase.execSQL(TapTruyen.TABLE_TAPTRUYEN);
        sqLiteDatabase.execSQL(TheLoai.TABLE_THELOAI);
        sqLiteDatabase.execSQL(ThuVien.TABLE_THUVIEN);
        sqLiteDatabase.execSQL(Truyen.TABLE_TRUYEN);
        sqLiteDatabase.execSQL(Truyen.TABLE_CT_TRUYEN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void execQuery(String query) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(query);
    }

    public Cursor rawQuery(String query) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    public int them(String table, ContentValues values) {
        SQLiteDatabase database = getWritableDatabase();
        return (int) database.insert(table, null, values);
    }

    public int sua(String table, ContentValues values, String whereClause, String[] whereClauses) {
        SQLiteDatabase database = getWritableDatabase();
        return database.update(table, values, whereClause, whereClauses);
    }

    public int xoa(String table, String whereClause, String[] whereClauses) {
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(table, whereClause, whereClauses);
    }
}
