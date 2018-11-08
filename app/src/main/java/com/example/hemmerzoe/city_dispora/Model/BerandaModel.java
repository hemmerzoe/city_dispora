package com.example.hemmerzoe.city_dispora.Model;

/**
 * Created by hemmerzoe on 23/11/2017.
 */

public class BerandaModel {
    String id_detailkategori;
    String nama_detail;
    String tanggal_dibuat;
    String nama_gambar;
//    String id_kategori1;

    public BerandaModel(String id_detailkategori, String nama_detail, String tanggal_dibuat, String nama_gambar) {
        this.id_detailkategori = id_detailkategori;
        this.nama_detail = nama_detail;
        this.tanggal_dibuat = tanggal_dibuat;
        this.nama_gambar = nama_gambar;
//        this.id_kategori = id_kategori;
//        this.id_kategori1 = id_kategori1;
    }

    public String getId_detailkategori() {
        return id_detailkategori;
    }

    public void setId_detailkategori(String id_detailkategori) {
        this.id_detailkategori = id_detailkategori;
    }

    public String getNama_detail() {
        return nama_detail;
    }

    public void setNama_detail(String nama_detail) {
        this.nama_detail = nama_detail;
    }

    public String getTanggal_dibuat() {
        return tanggal_dibuat;
    }

    public void setTanggal_dibuat(String tanggal_dibuat) {
        this.tanggal_dibuat = tanggal_dibuat;
    }

    public String getNama_gambar() {
        return nama_gambar;
    }

    public void setNama_gambar(String nama_gambar) {
        this.nama_gambar = nama_gambar;
    }

//    public String getId_kategori() {
//        return id_kategori;
//    }
//
//    public void setId_kategori(String id_kategori) {
//        this.id_kategori = id_kategori;
//    }


//    public String getId_kategori1() {
//        return id_kategori1;
//    }
//
//    public void setId_kategori1(String id_kategori1) {
//        this.id_kategori1 = id_kategori1;
//    }

}