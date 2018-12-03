package com.example.hemmerzoe.city_dispora.Model;

public class ModelEvent{
    private String id_event;
    private String judul_event;
    private String deskripsi;
    private String nama_gambar;

    //permasalahan ini itu harus sesuai dengan apa yang di panggil
    public ModelEvent(String id_event, String judul_event, String deskripsi, String nama_gambar) {
        this.id_event = id_event;
        this.judul_event = judul_event;
        this.deskripsi = deskripsi;
        this.nama_gambar = nama_gambar;
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

    public String getNama_gambar() {
        return nama_gambar;
    }

    public void setNama_gambar(String nama_gambar) {
        this.nama_gambar= nama_gambar;
    }

}
