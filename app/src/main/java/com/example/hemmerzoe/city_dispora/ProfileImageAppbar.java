package com.example.hemmerzoe.city_dispora;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hemmerzoe.city_dispora.utils.Tools;
import com.example.hemmerzoe.city_dispora.MenuDrawerNews;

public class ProfileImageAppbar extends AppCompatActivity {
    private ActionBar actionBar;
    private Toolbar toolbar;
    public  MenuDrawerNews menu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_image_appbar);

        //menu2.initNavigationMenu();
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Beranda");
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

//    private void initNavigationMenu() {
//        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
//        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
//            public void onDrawerOpened(View drawerView) {
//
//                super.onDrawerOpened(drawerView);
//            }
//        };
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(final MenuItem item) {
////                Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
////                actionBar.setTitle(item.getTitle());
//                drawer.closeDrawers();
//                switch (item.getItemId()){
//                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
//                    //dengan intent activity
//                    case R.id.nav_beranda:
//                        startActivity( new Intent(ProfileImageAppbar.this,MenuDrawerNews.class));
//                        finish();
////                        Toast.makeText(getApplicationContext(), "Beranda Telah Dipilih", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.nav_apotik:
//                        startActivity( new Intent(ProfileImageAppbar.this,ProfileImageAppbar.class));
//                        finish();
////                        Toast.makeText(getApplicationContext(),"Profil Telah Dipilih",Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.nav_wisata:
//                        Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
//                        actionBar.setTitle(item.getTitle());
//                        return true;
//                    case R.id.nav_hotel:
//                        Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
//                        actionBar.setTitle(item.getTitle());
//                        return true;
//                    case R.id.nav_kuliner:
//                        Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
//                        actionBar.setTitle(item.getTitle());
//                        return true;
//                    case R.id.nav_bank:
//                        Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
//                        actionBar.setTitle(item.getTitle());
//                        return true;
//                    default:
//                        Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
//                        actionBar.setTitle(item.getTitle());
//                        return true;
//                }
//            }
//        });
//
//        // open drawer at start
////        drawer.openDrawer(GravityCompat.START);
//    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
//        return true;
//    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
<<<<<<< HEAD
            Intent grid = new Intent(this, GridList.class);
            startActivity(grid);
//            menu2.initNavigationMenu();
=======
            Toast.makeText(getApplicationContext(), " Selected", Toast.LENGTH_SHORT).show();
>>>>>>> fde3378f6b3ab8023521a8177b277e2d7ce458ac
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
