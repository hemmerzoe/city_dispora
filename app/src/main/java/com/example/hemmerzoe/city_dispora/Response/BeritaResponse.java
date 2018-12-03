package com.example.hemmerzoe.city_dispora.Response;

import java.util.ArrayList;

public class BeritaResponse {
    public boolean error;

    public ArrayList<BeritaResponse.event> event;
    // public ArrayList<gambar> gambar;

//    public EventResponse(String judul_event) {
//    }
    //  public ArrayList<BerandaResponse.event> event;


    public static class event
    {
        public String id_event;
        public String judul_event;
        public String deskripsi;
        public String nama_gambar;
//        public String tanggal_perhelatan;
//        public String tanggal_dibuat_event;
//        public String id_admin;
    }
//    public class gambar
//    {
//        public String id_gambar,nama_gambar;
//    }
}
