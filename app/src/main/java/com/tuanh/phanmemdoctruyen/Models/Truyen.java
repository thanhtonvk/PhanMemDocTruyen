package com.tuanh.phanmemdoctruyen.Models;

import java.util.List;

public class Truyen {
    private int maTruyen;
    private String tenTruyen, tacGia, namSangTac;
    private String moTa;
    private String hinhAnh;
    private List<TheLoai> theLoaiList;
    private List<TapTruyen> tapTruyenList;

    public static Truyen TRUYEN;

    public static String TABLE_TRUYEN = "create table Truyen(" +
            "maTruyen integer primary key AUTOINCREMENT," +
            "tenTruyen ntext," +
            "tacGia ntext," +
            "namSangTac integer," +
            "moTa ntext," +
            "hinhAnh ntext)";
    public static String TABLE_CT_TRUYEN = "create table CTTruyen(" +
            "maCTTruyen integer primary key Autoincrement," +
            "maTruyen integer not null," +
            "maLoai integer not null)";

    public Truyen(int maTruyen, String tenTruyen, String tacGia, String namSangTac, String moTa, String hinhAnh) {
        this.maTruyen = maTruyen;
        this.tenTruyen = tenTruyen;
        this.tacGia = tacGia;
        this.namSangTac = namSangTac;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public Truyen(int maTruyen, String tenTruyen, String tacGia, String namSangTac, String moTa, String hinhAnh, List<TheLoai> theLoaiList, List<TapTruyen> tapTruyenList) {
        this.maTruyen = maTruyen;
        this.tenTruyen = tenTruyen;
        this.tacGia = tacGia;
        this.namSangTac = namSangTac;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.theLoaiList = theLoaiList;
        this.tapTruyenList = tapTruyenList;
    }

    public int getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(int maTruyen) {
        this.maTruyen = maTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNamSangTac() {
        return namSangTac;
    }

    public void setNamSangTac(String namSangTac) {
        this.namSangTac = namSangTac;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public List<TheLoai> getTheLoaiList() {
        return theLoaiList;
    }

    public void setTheLoaiList(List<TheLoai> theLoaiList) {
        this.theLoaiList = theLoaiList;
    }

    public List<TapTruyen> getTapTruyenList() {
        return tapTruyenList;
    }

    public void setTapTruyenList(List<TapTruyen> tapTruyenList) {
        this.tapTruyenList = tapTruyenList;
    }

    @Override
    public String toString() {
        return tenTruyen;
    }
}
