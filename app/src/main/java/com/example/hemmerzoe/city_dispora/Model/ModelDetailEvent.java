package com.example.hemmerzoe.city_dispora.Model;

public class ModelDetailEvent {
    private String id_event;
    private String judul_event;
    private String deskripsi;
    private String nama_gambar;
    private String tanggal_perhelatan;

    //permasalahan ini itu harus sesuai dengan apa yang di panggil
    public ModelDetailEvent(String id_event, String judul_event, String deskripsi, String nama_gambar, String tanggal_perhelatan) {
        this.id_event = id_event;
        this.judul_event = judul_event;
        this.deskripsi = deskripsi;
        this.nama_gambar = nama_gambar;
        this.tanggal_perhelatan=tanggal_perhelatan;
    }

    // harus didefinisikan set dan get nya sesuai construtor
    public String getId_event() {
        return id_event;
    }

    public void setId_event(String id_event) {
        this.id_event = id_event;
    }

    public String getJudul_event() {
        return judul_event;
    }

    public void setJudul_event(String judul_event) {
        this.judul_event = judul_event;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal_perhelatan() {
        return tanggal_perhelatan;
    }

    public void setTanggal_perhelatan(String deskripsi) {
        this.tanggal_perhelatan= tanggal_perhelatan;
    }

    public String getNama_gambar() {
        return nama_gambar;
    }

    public void setNama_gambar(String nama_gambar) {
        this.nama_gambar= nama_gambar;
    }

}
