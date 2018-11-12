
package com.example.hemmerzoe.city_dispora.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hemmerzoe.city_dispora.Detail_kategori;
import com.example.hemmerzoe.city_dispora.Model.Image;
import com.example.hemmerzoe.city_dispora.Model.ListKategoriModel;
import com.example.hemmerzoe.city_dispora.R;
import com.example.hemmerzoe.city_dispora.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ListKategoriModel> listkategori = new ArrayList<>();

    private OnLoadMoreListener onLoadMoreListener;
    String id_detailkategori,url;
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Image obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public KategoriAdapter(Context context, List<ListKategoriModel> listkategori) {
        this.listkategori = listkategori;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView brief;
        public View lyt_parent;
        public LinearLayout layar;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            name = (TextView) v.findViewById(R.id.name);
            brief = (TextView) v.findViewById(R.id.brief);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
            layar = (LinearLayout) v.findViewById(R.id.layar);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_image, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ListKategoriModel obj = listkategori.get(position);

        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            view.name.setText(obj.getNama_detail());
            view.brief.setText(obj.getTanggal_dibuat());
            if(obj.getId_kategori().equals("1")) {
                url = "http://smart.pasuruankota.go.id/_upload/apotik/" + obj.getNama_gambar();
            }
            if(obj.getId_kategori().equals("2")) {
                url = "http://smart.pasuruankota.go.id/_upload/wisata/" + obj.getNama_gambar();
            }
            if(obj.getId_kategori().equals("3")) {
                url = "http://smart.pasuruankota.go.id/_upload/hotel/" + obj.getNama_gambar();
            }
            if(obj.getId_kategori().equals("4")) {
                url = "http://smart.pasuruankota.go.id/_upload/kuliner/" + obj.getNama_gambar();
            }
            if(obj.getId_kategori().equals("5")) {
                url = "http://smart.pasuruankota.go.id/_upload/perbankan/" + obj.getNama_gambar();
            }
            if(obj.getId_kategori().equals("6")) {
                url = "http://smart.pasuruankota.go.id/_upload/rumahsakit/" + obj.getNama_gambar();
            }
            Glide.with(ctx)
                    .load(url)
                    .fitCenter() // menyesuaikan ukuran imageview
                    .crossFade() // animasi
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view.image);
//            Tools.displayImageOriginal(ctx, view.image, obj.image);
            view.layar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id_detailkategori = obj.getId_detailkategori();
                    Intent i = new Intent(ctx, Detail_kategori.class);
                    i.putExtra("key_iddetailkategori",id_detailkategori);
//                    hotel.putExtra("latitude",latitutde3);
//                    hotel.putExtra("longitude",longitude3);
//                    hotel.putExtra("alamat",alamat3);
                    ctx.startActivity(i);
                    Log.d("coba2 berhasil",id_detailkategori);
//                    Toast.makeText(view.getContext(),"testing", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(view.getContext(), new Gson().toJson(PengaduanModel), Toast.LENGTH_SHORT).show();

                }
            });
//            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (mOnItemClickListener != null) {
//                        mOnItemClickListener.onItemClick(view, listkategori.get(position), position);
//                    }
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return listkategori.size();
    }


    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int current_page);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}

