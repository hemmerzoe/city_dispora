package com.example.hemmerzoe.city_dispora;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hemmerzoe.city_dispora.Adapter.KategoriAdapter;
import com.example.hemmerzoe.city_dispora.Data.DataGenerator;
import com.example.hemmerzoe.city_dispora.Model.Image;
import com.example.hemmerzoe.city_dispora.Model.ListKategoriModel;
import com.example.hemmerzoe.city_dispora.Response.ListKategoriResponse;
import com.example.hemmerzoe.city_dispora.Retrofit.ApiService;
import com.example.hemmerzoe.city_dispora.Retrofit.ServiceGenerator;
import com.example.hemmerzoe.city_dispora.Widget.SpacingItemDecoration;
import com.example.hemmerzoe.city_dispora.utils.Tools;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Kategori_fragment extends Fragment{
    ApiService service;
    Call<ListKategoriResponse> CallBody;
    Context mContext;
    ListKategoriResponse model;


    private View parent_view;
    public View root_view;
    private RecyclerView recyclerView;
    private KategoriAdapter adapter;
    private ActionBar actionBar;
    ArrayList<ListKategoriModel> listkategori;

    public Kategori_fragment(){

    }
        public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

            View parent_view;
            parent_view = inflater.inflate(R.layout.kategori_fragment, container, false);
            setHasOptionsMenu(true);
            Bundle bundle = this.getArguments();
            listkategori = new ArrayList<>();

            DataListKategori(bundle.getString("key_idkategori"));

            recyclerView = (RecyclerView) parent_view.findViewById(R.id.recyclerView);
            // untuk grid listnya
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getActivity(), 3), true));
            recyclerView.setHasFixedSize(true);

//            List<Image> items = DataGenerator.getImageDate(getActivity());
//            items.addAll(DataGenerator.getImageDate(getActivity()));
            // items.addAll(DataGenerator.getImageDate(this));
            //   items.addAll(DataGenerator.getImageDate(this));

            //set data and list adapter
            adapter = new KategoriAdapter(getActivity(), listkategori);
            recyclerView.setAdapter(adapter);


//            // on item list clicked
//            adapter.setOnItemClickListener(new KategoriAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, Image obj, int position) {
//                    Log.d("coba2 berhasil",obj.name );
//                    //Snackbar.make(root_view, obj.name + " clicked", Snackbar.LENGTH_SHORT).show();
////                    Intent in = new Intent(getActivity(), Detail_berita.class);
////                    startActivity(in);
//                }
//            });
            return parent_view;
        }

        public void DataListKategori (String id_kategori)
        {
    //        Log.d("isi inputan", "nik: "+nik_member);
            service = ServiceGenerator.createService(ApiService.class);
            CallBody = service.listkategoriRequest(id_kategori);
            CallBody.enqueue(new Callback<ListKategoriResponse>() {
                @Override
                public void onResponse(Response<ListKategoriResponse> response) {
                    model = response.body();
                    if (model.error) {
                        Toast.makeText(getActivity(), "login gagal", Toast.LENGTH_SHORT).show();
                        Log.d("coba error", new Gson().toJson(response.body()));
                    }
                    if (!model.error) {
//                        Toast.makeText(getActivity(), new Gson().toJson(response.body()), Toast.LENGTH_SHORT).show();
                        for (int i=0;i<model.listkategori.size();i++){
                            listkategori.add(new ListKategoriModel(model.listkategori.get(i).id_detailkategori,
                                    model.listkategori.get(i).nama_detail,model.listkategori.get(i).tanggal_dibuat,
                                    model.listkategori.get(i).nama_gambar,model.listkategori.get(i).id_kategori));
                        }
//                        listkategori.add(new ListKategoriModel(model.apotik.get(0).id_detailkategori,model.apotik.get(0).nama_detail,
//                                model.apotik.get(0).tanggal_dibuat,model.apotik.get(0).nama_gambar));
                        adapter.notifyDataSetChanged();
                        Log.d("coba2 berhasil", new Gson().toJson(response.body()));
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("coba gagal", t.getMessage());
                }
            });
        }
    }