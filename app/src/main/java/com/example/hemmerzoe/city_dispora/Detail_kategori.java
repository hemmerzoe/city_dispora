package com.example.hemmerzoe.city_dispora;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hemmerzoe.city_dispora.Response.DetailKategoriResponse;
import com.example.hemmerzoe.city_dispora.Retrofit.ApiService;
import com.example.hemmerzoe.city_dispora.Retrofit.ServiceGenerator;
import com.example.hemmerzoe.city_dispora.utils.Tools;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Detail_kategori extends AppCompatActivity implements OnMapReadyCallback {
    ApiService service;
    Call<DetailKategoriResponse> CallBody;
    Context mContext;
    DetailKategoriResponse model;
    String url;
    ProgressDialog loading;

    private ActionBar actionBar;
    private TextView tv_judul,tv_deskripsi,tv_tgl,tv_alamat;
    private ImageView iv_kategori;
    private Toolbar toolbar;
    public Beranda menu2;
    private ImageView iv_foto,iv_foto2,iv_foto3,iv_foto4,iv_foto5;
    public GoogleMap mMap;
    public String nama_marker;
//    private Double latitude;
//    private Double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kategori);

        Bundle bundle = getIntent().getExtras();
        tv_judul        = findViewById(R.id.judul_kategori);
        tv_deskripsi    = findViewById(R.id.deskripsi_kategori);
        tv_tgl          = findViewById(R.id.tgl_kategori);
        tv_alamat       = findViewById(R.id.alamat_kategori);
        iv_kategori     = findViewById(R.id.gambar_kategori);

        //        foto
        iv_foto        = findViewById(R.id.image_1);
        iv_foto2        = findViewById(R.id.image_2);
        iv_foto3        = findViewById(R.id.image_3);
        iv_foto4        = findViewById(R.id.image_4);
        iv_foto5        = findViewById(R.id.image_5);

        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        DataDetailKategori(bundle.getString("key_iddetailkategori"));

//        Toast.makeText(Detail_kategori.this, bundle.getString("key_iddetailkategori"), Toast.LENGTH_SHORT).show();
        //menu2.initNavigationMenu();
        initToolbar();
//        initComponent();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapPlaces);
        mapFragment.getMapAsync(this);

        ((ImageView) findViewById(R.id.image_1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAbout();
            }
        });
        loading.dismiss();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // actionBar.setHomeButtonEnabled(true);
        Tools.setSystemBarColor(this);
//        menu2.initNavigationMenu();

    }

    private void initComponent() {
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_1), R.drawable.image_5);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_2), R.drawable.image_6);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_3), R.drawable.image_7);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_4), R.drawable.image_8);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_5), R.drawable.image_10);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent grid = new Intent(this, Beranda.class);
            startActivity(grid);
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void DataDetailKategori (String id_detailkategori)
    {
//        Log.d("isi inputan", "nik: "+nik_member);
        service = ServiceGenerator.createService(ApiService.class);
        CallBody = service.detailkategoriRequest(id_detailkategori);
        CallBody.enqueue(new Callback<DetailKategoriResponse>() {
            @Override
            public void onResponse(Response<DetailKategoriResponse> response) {
                model = response.body();
                if (model.error) {
                    Toast.makeText(Detail_kategori.this, "login gagal", Toast.LENGTH_SHORT).show();
                    Log.d("coba error", new Gson().toJson(response.body()));
                }
                if (!model.error) {
                    Double latitude,longitude;
                    latitude = Double.parseDouble(model.detailkategori.get(0).latitude);
                    longitude = Double.parseDouble(model.detailkategori.get(0).longitude);
                    // Add a marker in Sydney and move the camera
                    LatLng upd = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(upd).title(model.detailkategori.get(0).alamat));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(upd,12));

//                    Toast.makeText(Detail_kategori.this, Double.toString(lat), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Detail_kategori.this, model.detailkategori.get(0).longitude, Toast.LENGTH_SHORT).show();
                    tv_judul.setText(model.detailkategori.get(0).nama_detail);
                    tv_deskripsi.setText(model.detailkategori.get(0).deskripsi);
                    tv_tgl.setText(model.detailkategori.get(0).tanggal_dibuat);
                    tv_alamat.setText(model.detailkategori.get(0).alamat);
                    actionBar.setTitle(model.detailkategori.get(0).nama_detail);

                    if(model.detailkategori.get(0).id_kategori.equals("1")){
                        for (int i=0; i < model.gambar.size();i++){
                            if (i == 0){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/apotik/"+model.gambar.get(0).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto);
                                iv_foto.setVisibility(iv_foto.VISIBLE);
                            }
                            if (i == 1){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/apotik/"+model.gambar.get(1).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto2);
                                iv_foto2.setVisibility(iv_foto2.VISIBLE);
                            }
                            if (i == 2){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/apotik/"+model.gambar.get(2).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto3);
                                iv_foto3.setVisibility(iv_foto3.VISIBLE);
                            }
                            if (i == 3){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/apotik/"+model.gambar.get(3).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto4);
                                iv_foto4.setVisibility(iv_foto4.VISIBLE);
                            }
                            if (i == 4){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/apotik/"+model.gambar.get(4).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto5);
                                iv_foto5.setVisibility(iv_foto5.VISIBLE);
                            }
                        }
                        url = "http://smart.pasuruankota.go.id/_upload/apotik/"+model.gambar.get(0).nama_gambar;
                    }
                    if(model.detailkategori.get(0).id_kategori.equals("2")){
                        for (int i=0; i < model.gambar.size();i++){
                            if (i == 0){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/wisata/"+model.gambar.get(0).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto);
                                iv_foto.setVisibility(iv_foto.VISIBLE);
                            }
                            if (i == 1){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/wisata/"+model.gambar.get(1).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto2);
                                iv_foto2.setVisibility(iv_foto2.VISIBLE);
                            }
                            if (i == 2){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/wisata/"+model.gambar.get(2).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto3);
                                iv_foto3.setVisibility(iv_foto3.VISIBLE);
                            }
                            if (i == 3){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/wisata/"+model.gambar.get(3).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto4);
                                iv_foto4.setVisibility(iv_foto4.VISIBLE);
                            }
                            if (i == 4){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/wisata/"+model.gambar.get(4).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto5);
                                iv_foto5.setVisibility(iv_foto5.VISIBLE);
                            }
                        }
                        url = "http://smart.pasuruankota.go.id/_upload/wisata/"+model.gambar.get(0).nama_gambar;
                    }
                    if(model.detailkategori.get(0).id_kategori.equals("3")){
                        for (int i=0; i < model.gambar.size();i++){
                            if (i == 0){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/hotel/"+model.gambar.get(0).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto);
                                iv_foto.setVisibility(iv_foto.VISIBLE);
                            }
                            if (i == 1){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/hotel/"+model.gambar.get(1).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto2);
                                iv_foto2.setVisibility(iv_foto2.VISIBLE);
                            }
                            if (i == 2){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/hotel/"+model.gambar.get(2).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto3);
                                iv_foto3.setVisibility(iv_foto3.VISIBLE);
                            }
                            if (i == 3){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/hotel/"+model.gambar.get(3).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto4);
                                iv_foto4.setVisibility(iv_foto4.VISIBLE);
                            }
                            if (i == 4){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/hotel/"+model.gambar.get(4).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto5);
                                iv_foto5.setVisibility(iv_foto5.VISIBLE);
                            }
                        }
                        url = "http://smart.pasuruankota.go.id/_upload/hotel/"+model.gambar.get(0).nama_gambar;
                    }
                    if(model.detailkategori.get(0).id_kategori.equals("4")){
                        for (int i=0; i < model.gambar.size();i++){
                            if (i == 0){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/kuliner/"+model.gambar.get(0).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto);
                                iv_foto.setVisibility(iv_foto.VISIBLE);
                            }
                            if (i == 1){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/kuliner/"+model.gambar.get(1).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto2);
                                iv_foto2.setVisibility(iv_foto2.VISIBLE);
                            }
                            if (i == 2){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/kuliner/"+model.gambar.get(2).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto3);
                                iv_foto3.setVisibility(iv_foto3.VISIBLE);
                            }
                            if (i == 3){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/kuliner/"+model.gambar.get(3).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto4);
                                iv_foto4.setVisibility(iv_foto4.VISIBLE);
                            }
                            if (i == 4){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/kuliner/"+model.gambar.get(4).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto5);
                                iv_foto5.setVisibility(iv_foto5.VISIBLE);
                            }
                        }
                        url = "http://smart.pasuruankota.go.id/_upload/kuliner/"+model.gambar.get(0).nama_gambar;
                    }
                    if(model.detailkategori.get(0).id_kategori.equals("5")){
                        for (int i=0; i < model.gambar.size();i++){
                            if (i == 0){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/perbankan/"+model.gambar.get(0).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto);
                                iv_foto.setVisibility(iv_foto.VISIBLE);
                            }
                            if (i == 1){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/perbankan/"+model.gambar.get(1).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto2);
                                iv_foto2.setVisibility(iv_foto2.VISIBLE);
                            }
                            if (i == 2){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/perbankan/"+model.gambar.get(2).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto3);
                                iv_foto3.setVisibility(iv_foto3.VISIBLE);
                            }
                            if (i == 3){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/perbankan/"+model.gambar.get(3).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto4);
                                iv_foto4.setVisibility(iv_foto4.VISIBLE);
                            }
                            if (i == 4){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/perbankan/"+model.gambar.get(4).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto5);
                                iv_foto5.setVisibility(iv_foto5.VISIBLE);
                            }
                        }
                        url = "http://smart.pasuruankota.go.id/_upload/perbankan/"+model.gambar.get(0).nama_gambar;
                    }
                    if(model.detailkategori.get(0).id_kategori.equals("6")){
                        for (int i=0; i < model.gambar.size();i++){
                            if (i == 0){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/rumahsakit/"+model.gambar.get(0).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto);
                                iv_foto.setVisibility(iv_foto.VISIBLE);
                            }
                            if (i == 1){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/rumahsakit/"+model.gambar.get(1).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto2);
                                iv_foto2.setVisibility(iv_foto2.VISIBLE);
                            }
                            if (i == 2){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/rumahsakit/"+model.gambar.get(2).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto3);
                                iv_foto3.setVisibility(iv_foto3.VISIBLE);
                            }
                            if (i == 3){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/rumahsakit/"+model.gambar.get(3).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto4);
                                iv_foto4.setVisibility(iv_foto4.VISIBLE);
                            }
                            if (i == 4){
                                Glide.with(Detail_kategori.this)
                                        .load("http://smart.pasuruankota.go.id/_upload/rumahsakit/"+model.gambar.get(4).nama_gambar)
                                        .fitCenter() // menyesuaikan ukuran imageview
                                        .crossFade() // animasi
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(iv_foto5);
                                iv_foto5.setVisibility(iv_foto5.VISIBLE);
                            }
                        }
                        url = "http://smart.pasuruankota.go.id/_upload/rumahsakit/"+model.gambar.get(0).nama_gambar;
                    }



                    Glide.with(Detail_kategori.this)
                            .load(url)
                            .fitCenter() // menyesuaikan ukuran imageview
                            .crossFade() // animasi
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(iv_kategori);
                    Log.d("coba2 berhasil", new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(Detail_kategori.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("coba gagal", t.getMessage());
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng upd = new LatLng(latitude, longitude);
//        mMap.addMarker(new MarkerOptions().position(upd).title(nama_marker));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(upd,12));
    }

    private void showDialogAbout() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_image);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}
