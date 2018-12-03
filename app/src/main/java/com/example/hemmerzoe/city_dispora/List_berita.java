package com.example.hemmerzoe.city_dispora;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hemmerzoe.city_dispora.Adapter.AdapterListBerita;
import com.example.hemmerzoe.city_dispora.Model.ModelEvent;
import com.example.hemmerzoe.city_dispora.Response.EventResponse;
import com.example.hemmerzoe.city_dispora.Retrofit.ApiService;
import com.example.hemmerzoe.city_dispora.Retrofit.ServiceGenerator;
import com.example.hemmerzoe.city_dispora.utils.ItemAnimation;
import com.example.hemmerzoe.city_dispora.utils.Tools;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class List_berita extends AppCompatActivity {

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterListBerita mAdapter;
   // private List<People> items = new ArrayList<>();
    private int animation_type = ItemAnimation.FADE_IN;
    ApiService service;
    Call<EventResponse> CallBody;
    EventResponse model;
    ArrayList<ModelEvent> event12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_berita);
        parent_view = findViewById(android.R.id.content);
        event12 = new ArrayList<>();

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daftar Berita");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        Data_berita();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

//        items = DataGenerator.getPeopleData(this);
//        items.addAll(DataGenerator.getPeopleData(this));
//        items.addAll(DataGenerator.getPeopleData(this));
//        items.addAll(DataGenerator.getPeopleData(this));
//        items.addAll(DataGenerator.getPeopleData(this));

        animation_type = ItemAnimation.FADE_IN;
        setAdapter();
       // showSingleChoiceDialog();
    }

    private void setAdapter() {
        //set data and list adapter
        mAdapter = new AdapterListBerita(this, event12, animation_type);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListBerita.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ModelEvent obj, int position) {
                Snackbar.make(parent_view, "Item " + obj.getJudul_event() + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_refresh:
                setAdapter();
                break;
            case R.id.action_mode:
                showSingleChoiceDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String[] ANIMATION_TYPE = new String[]{
            "Bottom Up", "Fade In", "Left to Right", "Right to Left"
    };

    private void showSingleChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Daftar Berita");
        builder.setCancelable(false);
        builder.setSingleChoiceItems(ANIMATION_TYPE, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selected = ANIMATION_TYPE[i];
                if (selected.equalsIgnoreCase("Bottom Up")) {
                    animation_type = ItemAnimation.BOTTOM_UP;
                } else if (selected.equalsIgnoreCase("Fade In")) {
                    animation_type = ItemAnimation.FADE_IN;
                } else if (selected.equalsIgnoreCase("Left to Right")) {
                    animation_type = ItemAnimation.LEFT_RIGHT;
                } else if (selected.equalsIgnoreCase("Right to Left")) {
                    animation_type = ItemAnimation.RIGHT_LEFT;
                }
                getSupportActionBar().setTitle("Daftar Berita");
                setAdapter();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    public void Data_berita(){
        service = ServiceGenerator.createService(ApiService.class);
        CallBody = service.eventRequest();
        CallBody.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Response<EventResponse> response) {
                model = response.body();
                if (model.error) {
                    Toast.makeText(List_berita.this, "login gagal", Toast.LENGTH_SHORT).show();
                    Log.d("coba error", new Gson().toJson(response.body()));
                }
                if (!model.error) {
                    //Toast.makeText(List_event.this, new Gson().toJson(response.body()), Toast.LENGTH_SHORT).show();
                    if (new Gson().toJson(response.body().event).length() != 2) {
                        //String [] denis = new String[event12.size()];
                        for (int i = 0; i <model.event.size(); i++) {
                            // harus di panggil semua karena jika semua tidak dipanggil maka nilainya null
                            event12.add(new ModelEvent(model.event.get(i).id_event,model.event.get(i).judul_event,
                                    model.event.get(i).deskripsi, model.event.get(i).nama_gambar));
                        }
                        //Toast.makeText(List_event.this, " "+" "+judul_events, Toast.LENGTH_SHORT).show();
//
                        mAdapter.notifyDataSetChanged();
                        Log.d("coba2 berhasil", new Gson().toJson(response.body()));
                        //  Toast.makeText(List_event.this, judul_events.getText(), Toast.LENGTH_SHORT).show();
                        //    loading.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(List_berita.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("coba gagal", t.getMessage());
            }
        });
    }
}

