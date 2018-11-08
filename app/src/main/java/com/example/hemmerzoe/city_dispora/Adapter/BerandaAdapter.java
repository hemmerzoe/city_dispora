package com.example.hemmerzoe.city_dispora.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.example.hemmerzoe.city_dispora.Model.BerandaModel;
import com.example.hemmerzoe.city_dispora.R;

import java.util.List;

/**
 * Created by hemmerzoe on 23/11/2017.
 */

public class BerandaAdapter extends RecyclerView.Adapter<BerandaAdapter.ViewHolder> {
    List<BerandaModel> list;
    Context context;
    String value;
//    int no=1;

    public View view;

    public BerandaAdapter(List<BerandaModel> list, Context context) {
        this.context = context;
        this.list = list;
    }


    @Override
    public BerandaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_drawer_content, parent, false);
        BerandaAdapter.ViewHolder holder = new BerandaAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(BerandaAdapter.ViewHolder holder, int position) {
        BerandaModel beranda=list.get(position);
        String url;
//        if(position == 0) {
            holder.tv_judul.setText("name: " + beranda.getNama_detail());
            holder.tv_deskripsi.setText("nim: " + beranda.getTanggal_dibuat());
            url = "http://192.168.1.101/webcity_dispora/_upload/apotik/" + beranda.getNama_gambar();
            Glide.with(context)
                    .load(url)
                    .fitCenter() // menyesuaikan ukuran imageview
                    .crossFade() // animasi
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.iv_icon);
//        holder.tv_judul2.setText("name: " + beranda.getNama_detail1());
//        holder.tv_deskripsi2.setText("nim: " + beranda.getTanggal_dibuat1());
//        url = "http://192.168.1.101/webcity_dispora/_upload/apotik/" + beranda.getNama_gambar1();
//        Glide.with(context)
//                .load(url)
//                .fitCenter() // menyesuaikan ukuran imageview
//                .crossFade() // animasi
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.iv_icon2);

        holder.BerandaModel=beranda;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_judul,tv_deskripsi,tv_judul2,tv_deskripsi2;
        ImageView iv_icon,iv_icon2;
        BerandaModel BerandaModel;
        Button bt_apotik,bt_wisata;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tv_judul = (TextView) view.findViewById(R.id.judul_apotik);
            tv_deskripsi = (TextView) view.findViewById(R.id.tgl_apotik);
            iv_icon = (ImageView) view.findViewById(R.id.img_apotik);
            tv_judul2 = (TextView) view.findViewById(R.id.judul_wisata);
            tv_deskripsi2 = (TextView) view.findViewById(R.id.tgl_wisata);
            iv_icon2 = (ImageView) view.findViewById(R.id.img_wisata);
            bt_apotik = (Button) view.findViewById(R.id.but_apotik);
            bt_wisata = (Button) view.findViewById(R.id.but_wisata);

            bt_apotik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),BerandaModel.getNama_detail(), Toast.LENGTH_SHORT).show();
                }
            });
//            bt_wisata.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(view.getContext(),BerandaModel.getNama_detail1(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            tv_statusaduan = (Button) view.findViewById(R.id.status_aduan);

//                        tv_statusaduan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Toast.makeText(view.getContext(),"login gagal", Toast.LENGTH_SHORT).show();
//
//                }
//            });

        }
    }


}
