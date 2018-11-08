package com.example.hemmerzoe.city_dispora.Response;

import java.util.ArrayList;

public class BerandaResponse {
    public boolean error;

    public ArrayList<berita> berita;
    public ArrayList<apotik> apotik;
    public ArrayList<wisata> wisata;
    public ArrayList<hotel> hotel;
    public ArrayList<kuliner> kuliner;
    public ArrayList<perbankan> perbankan;
    public ArrayList<rumahsakit> rumahsakit;

    public class berita
    {
        public String id_berita,judul_berita;
    }

    public class apotik
    {
        public String id_detailkategori,nama_detail,tanggal_dibuat,nama_gambar,id_kategori;
    }

    public class wisata
    {
//        public String id_detailkategori,nama_detail,alamat,tanggal_dibuat,latitude,longitude,nama_gambar,id_kategori;
        public String id_detailkategori,nama_detail,tanggal_dibuat,nama_gambar,id_kategori;
    }

    public class hotel
    {
        public String id_detailkategori,nama_detail,tanggal_dibuat,nama_gambar,id_kategori;
    }

    public class kuliner
    {
        public String id_detailkategori,nama_detail,tanggal_dibuat,nama_gambar,id_kategori;
    }

    public class perbankan
    {
        public String id_detailkategori,nama_detail,tanggal_dibuat,nama_gambar,id_kategori;
    }

    public class rumahsakit
    {
        public String id_detailkategori,nama_detail,tanggal_dibuat,nama_gambar,id_kategori;
    }
}
