package com.tuanh.phanmemdoctruyen.Models;

import com.tuanh.phanmemdoctruyen.DAO.TaiKhoanDAO;

public class TaiKhoan {
    private String tenTaiKhoan, matKhau, sdt, email, hoTen;
    public static TaiKhoan TAI_KHOAN;
    public static String TABLE_TAIKHOAN = "create table TaiKhoan(" +
            "tenTaiKhoan varchar(50) primary key," +
            "matKhau varchar(50)," +
            "sdt char(15)," +
            "email varchar(50)," +
            "hoTen ntext)";

    public TaiKhoan(String tenTaiKhoan, String matKhau, String sdt, String email, String hoTen) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.sdt = sdt;
        this.email = email;
        this.hoTen = hoTen;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
