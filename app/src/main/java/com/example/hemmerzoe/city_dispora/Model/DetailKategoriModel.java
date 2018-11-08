package com.example.hemmerzoe.city_dispora.Model;

public class DetailKategoriModel {

    String id_detailkategori;
    String nama_detail;
    String alamat;
    String telepon;
    String deskripsi;
    String latitude;
    String longitude;
    String tanggal_dibuat;


    public DetailKategoriModel (String id_detailkategori, String nama_detail,
                                String alamat, String telepon,
                                String deskripsi, String latitude, String longitude,
                                String tanggal_dibuat) {
        this.id_detailkategori = id_detailkategori;
        this.nama_detail = nama_detail;
        this.alamat = alamat;
        this.telepon = telepon;
        this.deskripsi = deskripsi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tanggal_dibuat = tanggal_dibuat;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getLatitude() { return latitude; }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTanggal_dibuat() {
        return tanggal_dibuat;
    }

    public void setTanggal_dibuat(String tanggal_dibuat) {
        this.tanggal_dibuat = tanggal_dibuat;
    }
}
