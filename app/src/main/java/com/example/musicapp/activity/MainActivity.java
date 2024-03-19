package com.example.musicapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.musicapp.R;
import com.example.musicapp.databinding.ActivityMainBinding;
import com.example.musicapp.fragment.AllSongFragment;
import com.example.musicapp.fragment.ContactFragment;
import com.example.musicapp.fragment.FeaturedSongFragment;
import com.example.musicapp.fragment.FeedBackFragment;
import com.example.musicapp.fragment.HomeFragment;
import com.example.musicapp.fragment.NewSongFragment;
import com.example.musicapp.fragment.PopularFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding binding;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static final int TYPE_HOME = 1;
    public static final int TYPE_ALL_SONGS = 2;
    public static final int TYPE_FEATURED_SONGS = 3;
    public static final int TYPE_POPULAR_SONGS = 4;
    public static final int TYPE_NEW_SONGS = 5;
    public static final int TYPE_FEEDBACK = 6;
    public static final int TYPE_CONTACT = 7;

    private int current ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        toolbar = findViewById(R.id.toolbar_include);
//        drawerLayout = findViewById(R.id.drawer_layout);
//        setSupportActionBar(toolbar);
//        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//        navigationView = findViewById(R.id.nav_view);


//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        toolbar = binding.include.toolbarInclude;
//        setSupportActionBar(toolbar);
//
//        drawerLayout = binding.drawerLayout;
//        navigationView = binding.navView;
//        // thiết lập icon drawerNav
//        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if(id== R.id.nav_home){
//                    Toast.makeText(MainActivity.this, "Đây là Home", Toast.LENGTH_LONG).show();
//                }
//                if(id== R.id.nav_allsong){
//                    if( TYPE_ALL_SONGS != current){
//                        replaceFragment(AllSongFragment.class);
//                        current = TYPE_ALL_SONGS;
//                        initToolbar(R.string.all_song);
//                        showPlayAll();}
//                }
//                if(id== R.id.nav_contact){
//                    Toast.makeText(MainActivity.this, "Đây là Contact", Toast.LENGTH_LONG).show();
//                }
//                return true;
//            }
//        });
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = binding.include.toolbarInclude;
        setSupportActionBar(toolbar);
//toolbar.inflateMenu(R.menu.menu_toolbar);
        drawerLayout = binding.drawerLayout;
        navigationView = binding.navView;
        // thiết lập icon drawerNav
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //kiểm tra quyền
        checkNotificationPermission();
// gọi home fragment
        openHomeFragment();
//        //bắt sự kiện
       initlistener();
    }

    private void initlistener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id1 = item.getItemId();

                if (id1 == R.id.nav_home) {
                   // Toast.makeText(MainActivity.this, "Đây là Home", Toast.LENGTH_LONG).show();
                    if ( TYPE_HOME!= current) {
                        openHomeFragment();
                    }
                }
                else if (id1 == R.id.nav_allsong) {
                    if( TYPE_ALL_SONGS != current){
                        replaceFragment(AllSongFragment.class);
                        current = TYPE_ALL_SONGS;
                        initToolbar(R.string.all_song);
                        showPlayAll();}
                }
                else if (id1 == R.id.nav_featuresong) {
                    if(current!= TYPE_FEATURED_SONGS){
                        replaceFragment(FeaturedSongFragment.class);
                        current = TYPE_FEATURED_SONGS;
                        initToolbar(R.string.featured_song);
                        showPlayAll();}
                }
                else if (id1 == R.id.nav_feedback) {
                    if(current!= TYPE_FEEDBACK){
                        replaceFragment(FeedBackFragment.class);
                        current = TYPE_FEEDBACK;
                        initToolbar(R.string.feedback);
                        showPlayAll();}

                }
                else if (id1 == R.id.nav_contact) {
                    if(current != TYPE_CONTACT){
                        replaceFragment(ContactFragment.class);
                        current = TYPE_CONTACT;
                        initToolbar(R.string.contact);
                        showPlayAll();}
                }
                else if (id1 == R.id.nav_popularsong) {
                    if(current!= TYPE_POPULAR_SONGS){
                        replaceFragment(PopularFragment.class);
                        current = TYPE_POPULAR_SONGS;
                        initToolbar(R.string.popular);
                        showPlayAll();}
                }
                else  if (id1 == R.id.nav_newsong) {
                    if(current!= TYPE_NEW_SONGS){
                        replaceFragment(NewSongFragment.class);
                        current = TYPE_NEW_SONGS;
                        initToolbar(R.string.new_song);
                        showPlayAll();}

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



        binding.include.bottomControl.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.include.bottomControl.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.include.bottomControl.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.include.bottomControl.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.include.bottomControl.layoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.include.bottomControl.imgSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void openHomeFragment() {
        current = TYPE_HOME;
        replaceFragment(HomeFragment.class);
        initToolbar(R.string.music_app);
        showPlayAll();
        binding.navView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    private void showPlayAll() {
        switch (current) {
            case TYPE_ALL_SONGS:
                //toolbar.getMenu().findItem(R.id.play_all).setVisible(true);
                break;
            case TYPE_FEATURED_SONGS:
               // toolbar.getMenu().findItem(R.id.play_all).setVisible(true);
                break;
            case TYPE_NEW_SONGS:
              //  toolbar.getMenu().findItem(R.id.play_all).setVisible(true);
                break;
            case TYPE_POPULAR_SONGS:
             //  toolbar.getMenu().findItem(R.id.play_all).setVisible(true);
                break;
            default:
              //  toolbar.getMenu().findItem(R.id.play_all).setVisible(false);
                break;
        }
    }

    private void initToolbar(int str) {

       binding.include.toolbarInclude.setTitle(str);
    }

    private <T extends Fragment> void replaceFragment(Class<T> tClass) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host, tClass, null)
                .setReorderingAllowed(true)
                .commit();
    }

    private void checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }



}