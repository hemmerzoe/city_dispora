package com.example.hemmerzoe.city_dispora.Response;

import java.util.ArrayList;

public class ListKategoriResponse {
    public boolean error;

    public ArrayList<listkategori> listkategori;


    public class listkategori
    {
        public String id_detailkategori,nama_detail,tanggal_dibuat,nama_gambar,id_kategori;
    }

}