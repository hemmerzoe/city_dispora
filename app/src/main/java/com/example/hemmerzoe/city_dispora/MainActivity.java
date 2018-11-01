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
import com.example.hemmerzoe.city_dispora.Adapter.AdapterGridView;
import com.example.hemmerzoe.city_dispora.Model.Image;
import com.example.hemmerzoe.city_dispora.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActionBar actionBar;
    private Toolbar toolbar;

    private View parent_view;
    private ViewPager viewPager;
    private LinearLayout layout_dots;
    private MainActivity.AdapterImageSlider adapterImageSlider;
    private Runnable runnable = null;
    private Handler handler = new Handler();
    private RecyclerView recyclerView;
    private AdapterGridView mAdapter;
    private Toolbar toolbar_kategori;
    private NavigationView navigationView_kategori;

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
        setSupportActionBar(toolbar);
//        navigationView_kategori = (NavigationView) findViewById(R.id.nav_view);
        initToolbar();
        initComponent();

        initNavigationMenu();
        //initComponent2();

        ImageButton button1, button2, button3,button4, button5, button6;
        button1 = (ImageButton) findViewById(R.id.but_apotik);
        button1.setOnClickListener(this);
        button2 = (ImageButton) findViewById(R.id.but_wisata);
        button2.setOnClickListener(this);
        button3 = (ImageButton) findViewById(R.id.but_hotel);
        button3.setOnClickListener(this);
        button4 = (ImageButton) findViewById(R.id.but_kuliner);
        button4.setOnClickListener(this);
        button5 = (ImageButton) findViewById(R.id.but_bank);
        button5.setOnClickListener(this);
        button6 = (ImageButton) findViewById(R.id.but_rs);
        button6.setOnClickListener(this);

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
            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.but_apotik:
               Intent apotik = new Intent(this, MapsActivity.class);
               startActivity(apotik);
              //  actionBar.setTitle("Apotik");
                break;
            case R.id.but_wisata:
                Intent wisata = new Intent(this, Detail_kategori.class);
                startActivity(wisata);
               // actionBar.setTitle("Wisata");
                break;
            case R.id.but_hotel:
                Intent hotel = new Intent(this, Detail_kategori.class);
                startActivity(hotel);
//                actionBar.setTitle("Hotel");
                break;
            case R.id.but_kuliner:
                Intent kuliner = new Intent(this, Detail_kategori.class);
                startActivity(kuliner);
//                actionBar.setTitle("kuliner");
                break;
            case R.id.but_bank:
                Intent bank = new Intent(this, Detail_kategori.class);
                startActivity(bank);
//                actionBar.setTitle("Perbankan");
                break;
             default:
                 Intent rs = new Intent(this, Detail_kategori.class);
                 startActivity(rs);
//                 actionBar.setTitle("Rumah Sakit");
                 break;

        }

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
                switch (item.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.nav_beranda:
                        startActivity( new Intent(MainActivity.this,MainActivity.class));
                        finish();
                        return true;
                    case R.id.nav_apotik:
                        startActivity( new Intent(MainActivity.this,Detail_kategori.class));
                        finish();
                        return true;
                    case R.id.nav_wisata:
                        Kategori_fragment kategori_fragment_2 = new Kategori_fragment();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_2).commit();
                        actionBar.setTitle("Wisata");
                        return true;
                    case R.id.nav_hotel:
                        Kategori_fragment kategori_fragment_3 = new Kategori_fragment();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_3).commit();
                        actionBar.setTitle("Hotel");
                        return true;
                    case R.id.nav_kuliner:
                        Kategori_fragment kategori_fragment_4 = new Kategori_fragment();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_4).commit();
                        actionBar.setTitle("Kuliner");
                        return true;
                    case R.id.nav_bank:
                        Kategori_fragment kategori_fragment_5 = new Kategori_fragment();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.fragment_contain, kategori_fragment_5).commit();
                        actionBar.setTitle("Perbankan");
                        return true;
                    default:
//                        Kategori_fragment kategori_fragment_6 = new Kategori_fragment();
//                        transaction.addToBackStack(null);
//                        transaction.replace(R.id.fragment_contain, kategori_fragment_6).commit();
//                        actionBar.setTitle("Rumah Sakit");
                        Intent berita = new Intent(MainActivity.this, Detail_berita.class);
                        startActivity(berita);
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


    public boolean onOptionsItemSelected_2(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.but_apotik) {
//            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
           // Intent in = new Intent(this, );
            Kategori_fragment kategori_fragment_2 = new Kategori_fragment();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fragment_contain, kategori_fragment_2).commit();
            actionBar.setTitle("Apotik");
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
