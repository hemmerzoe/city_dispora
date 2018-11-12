package com.example.hemmerzoe.city_dispora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hemmerzoe.city_dispora.Adapter.KategoriAdapter;
import com.example.hemmerzoe.city_dispora.Model.Image;
import com.example.hemmerzoe.city_dispora.Response.BerandaResponse;
import com.example.hemmerzoe.city_dispora.Retrofit.ApiService;
import com.example.hemmerzoe.city_dispora.Retrofit.ServiceGenerator;
import com.example.hemmerzoe.city_dispora.utils.Tools;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class Beranda extends AppCompatActivity implements View.OnClickListener {
    ApiService service;
    Call<BerandaResponse> CallBody;
    Context mContext;
    BerandaResponse model;

    private ActionBar actionBar;
    private Toolbar toolbar;

    private View parent_view;
    private ViewPager viewPager;
    private LinearLayout layout_dots;
    private Beranda.AdapterImageSlider adapterImageSlider;
    private Runnable runnable = null;
    private Handler handler = new Handler();
    private RecyclerView recyclerView;
    private KategoriAdapter mAdapter;
    private Toolbar toolbar_kategori;
    private NavigationView navigationView_kategori;

    private TextView tv_apotik,tv_wisata,tv_hotel,tv_kuliner,tv_bank,tv_rs;
    private TextView tv_tglapotik,tv_tglwisata,tv_tglhotel,tv_tglkuliner,tv_tglbank,tv_tglrs;
    private ImageView iv_apotik,iv_wisata,iv_hotel,iv_kuliner,iv_bank,iv_rs;
    private ImageButton bt_apotik,bt_wisata,bt_hotel,bt_kuliner,bt_bank,bt_rs;
    private String id_apotik,id_wisata,id_hotel,id_kuliner,id_bank,id_rs;
//    private String latitutde,longitude,latitutde2,longitude2,latitutde3,longitude3,latitutde4,
//            longitude4,latitutde5,longitude5,latitutde6,longitude6;
//    private String alamat,alamat2,alamat3,alamat4,alamat5,alamat6;

    private static int[] array_image_place = {
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
    };

    private static String[] array_title_place = {
            "Dui fringilla ornare finibus, orci odio",
            "Mauris sagittis non elit quis fermentum",
            "Mauris ultricies augue sit amet est sollicitudin",
            "Suspendisse ornare est ac auctor pulvinar",
            "Vivamus laoreet aliquam ipsum eget pretium",
    };

    private static String[] array_brief_place = {
            "Foggy Hill",
            "The Backpacker",
            "River Forest",
            "Mist Mountain",
            "Side Park",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar_kategori = (Toolbar) findViewById(R.id.toolbar);

//        judul
        tv_apotik   = findViewById(R.id.judul_apotik);
        tv_wisata   = findViewById(R.id.judul_wisata);
        tv_hotel    = findViewById(R.id.judul_hotel);
        tv_kuliner  = findViewById(R.id.judul_kuliner);
        tv_bank     = findViewById(R.id.judul_bank);
        tv_rs       = findViewById(R.id.judul_rs);

//        tanggal
        tv_tglapotik    = findViewById(R.id.tgl_apotik);
        tv_tglwisata    = findViewById(R.id.tgl_wisata);
        tv_tglhotel     = findViewById(R.id.tgl_hotel);
        tv_tglkuliner   = findViewById(R.id.tgl_kuliner);
        tv_tglbank      = findViewById(R.id.tgl_bank);
        tv_tglrs        = findViewById(R.id.tgl_rs);

//        ikon
        iv_apotik   = findViewById(R.id.img_apotik);
        iv_wisata   = findViewById(R.id.img_wisata);
        iv_hotel    = findViewById(R.id.img_hotel);
        iv_kuliner  = findViewById(R.id.img_kuliner);
        iv_bank     = findViewById(R.id.img_bank);
        iv_rs       = findViewById(R.id.img_rs);

//        button
        bt_apotik   = findViewById(R.id.but_apotik);
        bt_wisata   = findViewById(R.id.but_wisata);
        bt_hotel    = findViewById(R.id.but_hotel);
        bt_kuliner  = findViewById(R.id.but_kuliner);
        bt_bank     = findViewById(R.id.but_bank);
        bt_rs       = findViewById(R.id.but_rs);

//        onclik
        bt_apotik.setOnClickListener(this);
        bt_wisata.setOnClickListener(this);
        bt_hotel.setOnClickListener(this);
        bt_kuliner.setOnClickListener(this);
        bt_bank.setOnClickListener(this);
        bt_rs.setOnClickListener(this);



        setSupportActionBar(toolbar);
        DataBeranda();
//        navigationView_kategori = (NavigationView) findViewById(R.id.nav_view);
        initToolbar();
        initComponent();
        initNavigationMenu();
        //initComponent2();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.but_apotik:
                if(id_apotik != null) {
                    Intent apotik = new Intent(this, Detail_kategori.class);
                    apotik.putExtra("key_iddetailkategori",id_apotik);
//                    apotik.putExtra("latitude",latitutde);
//                    apotik.putExtra("longitude",longitude);
//                    apotik.putExtra("alamat",alamat);
                    startActivity(apotik);
                    break;
                }
            case R.id.but_wisata:
                if(id_wisata != null) {
                    Intent wisata = new Intent(this, Detail_kategori.class);
                    wisata.putExtra("key_iddetailkategori",id_wisata);
//                    wisata.putExtra("latitude",latitutde2);
//                    wisata.putExtra("longitude",longitude2);
//                    wisata.putExtra("alamat",alamat2);
                    startActivity(wisata);
                    break;
                }
            case R.id.but_hotel:
                if(id_hotel != null) {
                    Intent hotel = new Intent(this, Detail_kategori.class);
                    hotel.putExtra("key_iddetailkategori",id_hotel);
//                    hotel.putExtra("latitude",latitutde3);
//                    hotel.putExtra("longitude",longitude3);
//                    hotel.putExtra("alamat",alamat3);
                    startActivity(hotel);
                    break;
                }
            case R.id.but_kuliner:
                if(id_kuliner != null) {
                    Intent kuliner = new Intent(this, Detail_kategori.class);
                    kuliner.putExtra("key_iddetailkategori",id_kuliner);
//                    kuliner.putExtra("latitude",latitutde4);
//                    kuliner.putExtra("longitude",longitude4);
//                    kuliner.putExtra("alamat",alamat4);
                    startActivity(kuliner);
                    break;
                }
            case R.id.but_bank:
                if(id_bank != null) {
                    Intent bank = new Intent(this, Detail_kategori.class);
                    bank.putExtra("key_iddetailkategori",id_bank);
//                    bank.putExtra("latitude",latitutde5);
//                    bank.putExtra("longitude",longitude5);
//                    bank.putExtra("alamat",alamat5);
                    startActivity(bank);
                    break;
                }
            case R.id.but_rs:
                if(id_rs != null) {
                    Intent rs = new Intent(this, Detail_kategori.class);
                    rs.putExtra("key_iddetailkategori", id_rs);
//                    rs.putExtra("latitude",latitutde6);
//                    rs.putExtra("longitude",longitude6);
//                    rs.putExtra("alamat",alamat6);
                    startActivity(rs);
                    break;
                }
            default:
                if(id_rs != null) {
                    Intent rs = new Intent(this, Detail_kategori.class);
                    rs.putExtra("key_iddetailkategori",id_rs);
//                    rs.putExtra("latitude",latitutde6);
//                    rs.putExtra("longitude",longitude6);
//                    rs.putExtra("alamat",alamat6);
                    startActivity(rs);
                    break;
                }
        }

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Beranda");
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        layout_dots = (LinearLayout) findViewById(R.id.layout_dots);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapterImageSlider = new AdapterImageSlider(this, new ArrayList<Image>());

        final List<Image> items = new ArrayList<>();
        for (int i = 0; i < array_image_place.length; i++) {
            Image obj = new Image();
            obj.image = array_image_place[i];
            obj.imageDrw = getResources().getDrawable(obj.image);
            obj.name = array_title_place[i];
            obj.brief = array_brief_place[i];
            items.add(obj);
        }
//
        adapterImageSlider.setItems(items);
        viewPager.setAdapter(adapterImageSlider);
//
        // displaying selected image first
        viewPager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        ((TextView) findViewById(R.id.title)).setText(items.get(0).name);
        ((TextView) findViewById(R.id.brief)).setText(items.get(0).brief);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                ((TextView) findViewById(R.id.title)).setText(items.get(pos).name);
                ((TextView) findViewById(R.id.brief)).setText(items.get(pos).brief);
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }
            //
//            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        startAutoSlider(adapterImageSlider.getCount());
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 0, 10, 0);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle_outline);
            dots[i].setColorFilter(ContextCompat.getColor(this, R.color.grey_40), PorterDuff.Mode.SRC_ATOP);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setImageResource(R.drawable.shape_circle);
            dots[current].setColorFilter(ContextCompat.getColor(this, R.color.grey_40), PorterDuff.Mode.SRC_ATOP);
        }
    }
    //
    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (item.getItemId() == android.R.id.home) {
//            finish();
            //belum masuk drawer, ini hanya ngelink
            Intent home = new Intent(this, Beranda.class);
            startActivity(home);
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private static class AdapterImageSlider extends PagerAdapter {

        private Activity act;
        private List<Image> items;

        private AdapterImageSlider.OnItemClickListener onItemClickListener;

        private interface OnItemClickListener {
            void onItemClick(View view, Image obj);
        }

        public void setOnItemClickListener(AdapterImageSlider.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        // constructor
        private AdapterImageSlider(Activity activity, List<Image> items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        public Image getItem(int pos) {
            return items.get(pos);
        }

        public void setItems(List<Image> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final Image o = items.get(position);
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_slider_image, container, false);

            ImageView image = (ImageView) v.findViewById(R.id.image);
            MaterialRippleLayout lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
            Tools.displayImageOriginal(act, image, o.image);
            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, o);
                    }
                }
            });

            ((ViewPager) container).addView(v);

            return v;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);

        }
    }
    @Override
    public void onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    public void initNavigationMenu() {
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        View hview =  nav_view.getHeaderView(0);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
//                Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
//                actionBar.setTitle(item.getTitle());
                drawer.closeDrawers();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                switch (item.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.nav_beranda:
                        startActivity( new Intent(Beranda.this,Beranda.class));
                        finish();
                        return true;
                    case R.id.nav_apotik:
                        Kategori_fragment kategori_fragment_1 = new Kategori_fragment();
//                        Bundle bundle = new Bundle();
                        bundle.putString("key_idkategori", "1");
                        transaction.addToBackStack(null);
                        kategori_fragment_1.setArguments(bundle);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_1).commit();
                        actionBar.setTitle("Apotik");
                        return true;
                    case R.id.nav_wisata:
                        Kategori_fragment kategori_fragment_2 = new Kategori_fragment();
                        transaction.addToBackStack(null);
                        bundle.putString("key_idkategori", "2");
                        transaction.addToBackStack(null);
                        kategori_fragment_2.setArguments(bundle);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_2).commit();
                        actionBar.setTitle("Wisata");
                        return true;
                    case R.id.nav_hotel:
                        Kategori_fragment kategori_fragment_3 = new Kategori_fragment();
                        transaction.addToBackStack(null);
                        bundle.putString("key_idkategori", "3");
                        transaction.addToBackStack(null);
                        kategori_fragment_3.setArguments(bundle);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_3).commit();
                        actionBar.setTitle("Hotel");
                        return true;
                    case R.id.nav_kuliner:
                        Kategori_fragment kategori_fragment_4 = new Kategori_fragment();
                        transaction.addToBackStack(null);
                        bundle.putString("key_idkategori", "4");
                        transaction.addToBackStack(null);
                        kategori_fragment_4.setArguments(bundle);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_4).commit();
                        actionBar.setTitle("Kuliner");
                        return true;
                    case R.id.nav_bank:
                        Kategori_fragment kategori_fragment_5 = new Kategori_fragment();
                        transaction.addToBackStack(null);
                        bundle.putString("key_idkategori", "5");
                        transaction.addToBackStack(null);
                        kategori_fragment_5.setArguments(bundle);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_5).commit();
                        actionBar.setTitle("Perbankan");
                        return true;
                    case R.id.nav_rs:
                        Kategori_fragment kategori_fragment_6 = new Kategori_fragment();
                        transaction.addToBackStack(null);
                        bundle.putString("key_idkategori", "6");
                        kategori_fragment_6.setArguments(bundle);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_6).commit();
                        actionBar.setTitle("Rumah Sakit");
                        return true;
                    case R.id.nav_berita:
                        Intent berita = new Intent(Beranda.this, Activity_daftar_berita.class);
                        startActivity(berita);
                    default:
                        Intent event = new Intent(Beranda.this, Detail_berita.class);
                        startActivity(event);
                        return true;
                }
            }
        });

        // open drawer at start
//        drawer.openDrawer(GravityCompat.START);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Kategori_fragment(), "KATEGORI");
//        adapter.addFragment(new LayananKk(), "Kartu Keluarga");
//        adapter.addFragment(new LayananAkteLahir(), "Akte Lahir");
//        adapter.addFragment(new LayananAkteMati(), "Akte Mati");
        viewPager.setAdapter(adapter);
    }

    public void DataBeranda ()
    {
//        Log.d("isi inputan", "nik: "+nik_member);
        service = ServiceGenerator.createService(ApiService.class);
        CallBody = service.berandaRequest();
        CallBody.enqueue(new Callback<BerandaResponse>() {
            @Override
            public void onResponse(Response<BerandaResponse> response) {
                model = response.body();
                if (model.error) {
                    Toast.makeText(Beranda.this, "login gagal", Toast.LENGTH_SHORT).show();
                    Log.d("coba error", new Gson().toJson(response.body()));
                }
                if (!model.error) {
                   // Toast.makeText(Beranda.this, new Gson().toJson(response.body()), Toast.LENGTH_SHORT).show();
                    if(new Gson().toJson(response.body().apotik).length() != 2){
                        tv_apotik.setText(model.apotik.get(0).nama_detail);
                        tv_tglapotik.setText(model.apotik.get(0).tanggal_dibuat);
                        Glide.with(Beranda.this)
                                .load("http://smart.pasuruankota.go.id/_upload/apotik/"+model.apotik.get(0).nama_gambar)
                                .fitCenter() // menyesuaikan ukuran imageview
                                .crossFade() // animasi
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(iv_apotik);
                        id_apotik   = model.apotik.get(0).id_detailkategori;
                    }
                    if(new Gson().toJson(response.body().wisata).length() != 2){
                        tv_wisata.setText(model.wisata.get(0).nama_detail);
                        tv_tglwisata.setText(model.wisata.get(0).tanggal_dibuat);
                        Glide.with(Beranda.this)
                                .load("http://smart.pasuruankota.go.id/_upload/wisata/"+model.wisata.get(0).nama_gambar)
                                .fitCenter() // menyesuaikan ukuran imageview
                                .crossFade() // animasi
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(iv_wisata);
                        id_wisata   = model.wisata.get(0).id_detailkategori;
                    }
                    if(new Gson().toJson(response.body().hotel).length() != 2){
                        tv_hotel.setText(model.hotel.get(0).nama_detail);
                        tv_tglhotel.setText(model.hotel.get(0).tanggal_dibuat);
                        Glide.with(Beranda.this)
                                .load("http://smart.pasuruankota.go.id/_upload/hotel/"+model.hotel.get(0).nama_gambar)
                                .fitCenter() // menyesuaikan ukuran imageview
                                .crossFade() // animasi
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(iv_hotel);
                        id_hotel    = model.hotel.get(0).id_detailkategori;
                    }
                    if(new Gson().toJson(response.body().kuliner).length() != 2){
                        tv_kuliner.setText(model.kuliner.get(0).nama_detail);
                        tv_tglkuliner.setText(model.kuliner.get(0).tanggal_dibuat);
                        Glide.with(Beranda.this)
                                .load("http://smart.pasuruankota.go.id/_upload/kuliner/"+model.kuliner.get(0).nama_gambar)
                                .fitCenter() // menyesuaikan ukuran imageview
                                .crossFade() // animasi
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(iv_kuliner);
                        id_kuliner  = model.kuliner.get(0).id_detailkategori;
                    }
                    if(new Gson().toJson(response.body().perbankan).length() != 2){
                        tv_bank.setText(model.perbankan.get(0).nama_detail);
                        tv_tglbank.setText(model.perbankan.get(0).tanggal_dibuat);
                        Glide.with(Beranda.this)
                                .load("http://smart.pasuruankota.go.id/_upload/perbankan/"+model.perbankan.get(0).nama_gambar)
                                .fitCenter() // menyesuaikan ukuran imageview
                                .crossFade() // animasi
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(iv_bank);
                        id_bank     = model.perbankan.get(0).id_detailkategori;
                    }
                    if(new Gson().toJson(response.body().rumahsakit).length() != 2){
                        tv_rs.setText(model.rumahsakit.get(0).nama_detail);
                        tv_tglrs.setText(model.rumahsakit.get(0).tanggal_dibuat);
                        Glide.with(Beranda.this)
                                .load("http://smart.pasuruankota.go.id/_upload/rumahsakit/"+model.rumahsakit.get(0).nama_gambar)
                                .fitCenter() // menyesuaikan ukuran imageview
                                .crossFade() // animasi
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(iv_rs);
                        id_rs       = model.rumahsakit.get(0).id_detailkategori;
                    }

                    Log.d("coba2 berhasil", "gagal");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(Beranda.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("coba gagal", t.getMessage());
            }
        });
    }
}
