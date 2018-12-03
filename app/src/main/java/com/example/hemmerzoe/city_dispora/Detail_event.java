package com.example.hemmerzoe.city_dispora;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hemmerzoe.city_dispora.Response.DetailEventResponse;
import com.example.hemmerzoe.city_dispora.Response.EventResponse;
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

public class Detail_event extends AppCompatActivity implements OnMapReadyCallback{
    ApiService service;
    Call<DetailEventResponse> CallBody;
    EventResponse model1;
    DetailEventResponse model;

    // public TextView id_event2,judul_event2,deskripsi2,nama_gambar2;
    private TextView id_event2,judul_event,deskripsi,nama_gambar,tanggal_perhelatan;
    public ImageView image_event;
    ProgressDialog loading;
    double latitude;
    double longitude;
    public GoogleMap mMap;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        Bundle bundle = getIntent().getExtras();
        //id_event2= (TextView) findViewById(R.id.judul_event);
        judul_event             = findViewById(R.id.judul_event);
        deskripsi               = findViewById(R.id.deskripsi_event);
        image_event             =  findViewById(R.id.image_event);
        tanggal_perhelatan      =  findViewById(R.id.tanggal_perhelatan);
//        Toast.makeText(this, bundle.getString("key_id_event"), Toast.LENGTH_SHORT).show();
        // loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        DatadetailEvent(bundle.getString("key_id_event"));
        //DataCoba();
        initToolbar();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapPlaces);
        mapFragment.getMapAsync(this);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Berita");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent berita_beranda = new Intent(Detail_event.this, Beranda.class);
            startActivity(berita_beranda);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void DatadetailEvent(String id_event) {
        service = ServiceGenerator.createService(ApiService.class);
        CallBody = service.eventRequestID(id_event);
        CallBody.enqueue(new Callback<DetailEventResponse>() {
            @Override
            public void onResponse(Response<DetailEventResponse> response) {
                model = response.body();
                if (model.error) {
                    Toast.makeText(Detail_event.this, "login gagal", Toast.LENGTH_SHORT).show();
                    Log.d("coba error", new Gson().toJson(response.body()));
                }
                if (!model.error) {
                    if(new Gson().toJson(response.body().detailevent).length() != 2){
                        //Toast.makeText(Detail_event.this, new Gson().toJson(response.body()), Toast.LENGTH_SHORT).show();
                        // id_event2.setText(model.detailevent.get(0).id_event);
                        latitude = Double.parseDouble(model.detailevent.get(0).latitude);
                        longitude = Double.parseDouble(model.detailevent.get(0).longitude);
                        // Add a marker in Sydney and move the camera
                        LatLng upd = new LatLng(latitude, longitude);
                        mMap.addMarker(new MarkerOptions().position(upd).title(model.detailevent.get(0).judul_event));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(upd,12));

                        judul_event.setText(model.detailevent.get(0).judul_event);
                        deskripsi.setText(model.detailevent.get(0).deskripsi);
                        tanggal_perhelatan.setText(model.detailevent.get(0).tanggal_perhelatan);
                        Glide.with(Detail_event.this)
                                .load("http://data.pasuruankota.go.id/_upload/dispora/event/"+model.detailevent.get(0).nama_gambar)
                                .fitCenter() // menyesuaikan ukuran imageview
                                .crossFade() // animasi
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(image_event);

                        //Toast.makeText(Detail_berita.this, " "+" "+judul_event2, Toast.LENGTH_SHORT).show();
                    }
                    // mAdapter.notifyDataSetChanged();
                    Log.d("coba2 berhasil", new Gson().toJson(response.body()));
                    //  Toast.makeText(List_event.this, judul_events.getText(), Toast.LENGTH_SHORT).show();
                    //    loading.dismiss();
                }
                //}
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(Detail_event.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("coba gagal", t.getMessage());
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

//    public void DataCoba(){
//        service = ServiceGenerator.createService(ApiService.class);
//       // CallBody = service.eventRequestID();
//        CallBody.enqueue(new Callback<EventResponse>() {
//            @Override
//            public void onResponse(Response<EventResponse> response) {
//                model1 = response.body();
//                if (model.error) {
//                    Toast.makeText(Detail_berita.this, "login gagal", Toast.LENGTH_SHORT).show();
//                    Log.d("coba error", new Gson().toJson(response.body()));
//                }
//                if (!model1.error) {
//                    if(new Gson().toJson(response.body().event).length() != 2){
////                        id_event2.setText(model.event.get(0).id_event);
////                        judul_event2.setText(model.event.get(0).judul_event);
////                        deskripsi2.setText(model.event.get(0).deskripsi);
//                       // nama_gambar2.setText(model.event.get(0).nama_gambar);
////                        Glide.with(Beranda.this)
////                                .load("http://data.pasuruankota.go.id/_upload/dispora/apotik/"+model.apotik.get(0).nama_gambar)
////                                .fitCenter() // menyesuaikan ukuran imageview
////                                .crossFade() // animasi
////                                .diskCacheStrategy(DiskCacheStrategy.ALL)
////                                .into(iv_apotik);
//                       // id_apotik   = model.apotik.get(0).id_detailkategori;
//                    }
////                    id_event2.setText(model.event.get(0).id_event);
////                    judul_event2.setText(model.event.get(0).judul_event);
////                    deskripsi2.setText(model.event.get(0).deskripsi);
////                    nama_gambar2.setText(model.event.get(0).nama_gambar);
////                        Glide.with(Detail_berita.this)
////                                .load("http://data.pasuruankota.go.id/_upload/dispora/event/"+model.event.get(0).nama_gambar)
////                                .fitCenter() // menyesuaikan ukuran imageview
////                                .crossFade() // animasi
////                                .diskCacheStrategy(DiskCacheStrategy.ALL)
////                                .into(image_event);
//
//                  // Toast.makeText(Detail_berita.this, " "+" "+judul_event2, Toast.LENGTH_SHORT).show();
////
//                    // mAdapter.notifyDataSetChanged();
//                    Log.d("coba2 berhasil", new Gson().toJson(response.body()));
//                    //  Toast.makeText(List_event.this, judul_events.getText(), Toast.LENGTH_SHORT).show();
//                    //    loading.dismiss();
//                }
//                //}
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Toast.makeText(Detail_berita.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("coba gagal", t.getMessage());
//            }
//        });
//    }
}
