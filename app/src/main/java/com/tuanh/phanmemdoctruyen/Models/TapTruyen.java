package com.tuanh.phanmemdoctruyen.Models;

public class TapTruyen {
    private int maTap;
    private String tenTap;
    private String noiDung;
    public static TapTruyen TAP_TRUYEN;
    public static String TABLE_TAPTRUYEN = "create table TapTruyen(" +
            "maTap INTEGER primary key autoincrement," +
            "tenTap ntext," +
            "noiDung ntext," +
            "maTruyen INTEGER not null)";

    public TapTruyen(String tenTap, String noiDung) {
        this.tenTap = tenTap;
        this.noiDung = noiDung;
    }

    public TapTruyen(int maTap, String tenTap, String noiDung) {
        this.maTap = maTap;
        this.tenTap = tenTap;
        this.noiDung = noiDung;
    }

    public int getMaTap() {
        return maTap;
    }

    public void setMaTap(int maTap) {
        this.maTap = maTap;
    }

    public String getTenTap() {
        return tenTap;
    }

    public void setTenTap(String tenTap) {
        this.tenTap = tenTap;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public String toString() {
        return tenTap;
    }
}
