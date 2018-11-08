package com.example.hemmerzoe.city_dispora;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hemmerzoe.city_dispora.Adapter.AdapterGridView;
import com.example.hemmerzoe.city_dispora.Data.DataGenerator;
import com.example.hemmerzoe.city_dispora.Model.Image;
import com.example.hemmerzoe.city_dispora.Widget.SpacingItemDecoration;
import com.example.hemmerzoe.city_dispora.utils.Tools;
import com.google.gson.Gson;

import java.util.List;

public class Kategori_fragment extends Fragment{

    private View parent_view;
    public View root_view;
    private RecyclerView recyclerView;
    private AdapterGridView mAdapter;
    private ActionBar actionBar;

    public Kategori_fragment(){

    }
        public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

            View parent_view;
            parent_view = inflater.inflate(R.layout.kategori_fragment, container, false);
            setHasOptionsMenu(true);

            recyclerView = (RecyclerView) parent_view.findViewById(R.id.recyclerView);
            // untuk grid listnya
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getActivity(), 3), true));
            recyclerView.setHasFixedSize(true);

            List<Image> items = DataGenerator.getImageDate(getActivity());
            items.addAll(DataGenerator.getImageDate(getActivity()));
            // items.addAll(DataGenerator.getImageDate(this));
            //   items.addAll(DataGenerator.getImageDate(this));

            //set data and list adapter
            mAdapter = new AdapterGridView(getActivity(), items);
            recyclerView.setAdapter(mAdapter);
            // on item list clicked
            mAdapter.setOnItemClickListener(new AdapterGridView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, Image obj, int position) {
                    Log.d("coba2 berhasil",obj.name );
                    //Snackbar.make(root_view, obj.name + " clicked", Snackbar.LENGTH_SHORT).show();
//                    Intent in = new Intent(getActivity(), Detail_berita.class);
//                    startActivity(in);
                }
            });
            return parent_view;
        }
    }