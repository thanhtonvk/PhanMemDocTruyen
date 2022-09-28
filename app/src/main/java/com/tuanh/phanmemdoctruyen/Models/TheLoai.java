package com.tuanh.phanmemdoctruyen.Models;

public class TheLoai {
    private int maLoai;
    private String tenLoai;
    private String hinhAnh;
    public static TheLoai THELOAI;
    public static String TABLE_THELOAI = "create table TheLoai(" +
            "maLoai INTEGER primary key autoincrement," +
            "tenLoai ntext," +
            "hinhAnh ntext)";


    public TheLoai(int maLoai, String tenLoai, String hinhAnh) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.hinhAnh = hinhAnh;
    }

    public TheLoai(String tenLoai, String hinhAnh) {
        this.tenLoai = tenLoai;
        this.hinhAnh = hinhAnh;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return tenLoai;
    }
}
