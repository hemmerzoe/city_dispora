package com.example.hemmerzoe.city_dispora.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hemmerzoe.city_dispora.Detail_berita;
import com.example.hemmerzoe.city_dispora.Model.ModelEvent;
import com.example.hemmerzoe.city_dispora.R;
import com.example.hemmerzoe.city_dispora.utils.ItemAnimation;

import java.util.ArrayList;
import java.util.List;

public class AdapterListBerita extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   // private List<People> items = new ArrayList<>();
    List<ModelEvent> berita = new ArrayList<>();
    String url, id_event;
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
    private int animation_type = 0;

    public interface OnItemClickListener {
        void onItemClick(View view, ModelEvent obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBerita(Context context, List<ModelEvent> items, int animation_type) {
        this.berita = items;
        ctx = context;
        this.animation_type = animation_type;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public View lyt_parent;
        public TextView description;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            name = (TextView) v.findViewById(R.id.name);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
            description =(TextView)v.findViewById(R.id.description);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daftar_berita, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.e("onBindViewHolder", "onBindViewHolder : " + position);

        final ModelEvent obj = berita.get(position);
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            //People p = items.get(position);
            view.name.setText(obj.getJudul_event());
            view.description.setText(obj.getDeskripsi());
            url = "http://data.pasuruankota.go.id/_upload/dispora/event/" + obj.getNama_gambar();
            Glide.with(ctx)
                    .load(url)
                    .fitCenter() // menyesuaikan ukuran imageview
                    .crossFade() // animasi
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view.image);
            //Tools.displayImageRound(ctx, view.image, p.image);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id_event= obj.getId_event();
                    Intent i = new Intent(ctx, Detail_berita.class);
                    i.putExtra("key_id_event",id_event);
                    ctx.startActivity(i);
                    Log.d("coba2 berhasil",id_event);

                    //Toast.makeText(view.getContext(),id_event, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(view.getContext(), new Gson().toJson(PengaduanModel), Toast.LENGTH_SHORT).show();

                }
//                public void onClick(View view) {
//                    if (mOnItemClickListener != null) {
//                        mOnItemClickListener.onItemClick(view, berita.get(position), position);
//                    }
//                }
            });
            setAnimation(view.itemView, position);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return berita.size();
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }
}
