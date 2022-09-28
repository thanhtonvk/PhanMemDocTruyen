package com.tuanh.phanmemdoctruyen.Models;

import java.util.List;

public class ThuVien {
    private int maThuVien;
    private Truyen truyen;

    public static String TABLE_THUVIEN = "create table ThuVien(" +
            "maThuVien INTEGER primary key autoincrement," +
            "tenTaiKhoan varchar(50) not null," +
            "maTruyen INTEGER not null)";


    public ThuVien(int maThuVien, Truyen truyen) {
        this.maThuVien = maThuVien;
        this.truyen = truyen;
    }

    public ThuVien() {

    }

    public int getMaThuVien() {
        return maThuVien;
    }

    public void setMaThuVien(int maThuVien) {
        this.maThuVien = maThuVien;
    }

    public Truyen getTruyen() {
        return truyen;
    }

    public void setTruyen(Truyen truyen) {
        this.truyen = truyen;
    }
}
