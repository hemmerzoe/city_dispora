package com.example.hemmerzoe.city_dispora.Response;

import java.util.ArrayList;

public class DetailKategoriResponse {
    public boolean error;

    public ArrayList<detailkategori> detailkategori;
    public ArrayList<gambar> gambar;

    public class detailkategori
    {
        public String id_detailkategori,nama_detail,alamat,telepon,status_detail,deskripsi,
        latitude,longitude,tanggal_dibuat,id_kategori;
    }

    public class gambar
    {
        public String id_gambar,nama_gambar;
    }
}

