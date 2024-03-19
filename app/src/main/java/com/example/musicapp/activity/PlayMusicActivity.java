package com.example.musicapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.musicapp.R;
import com.example.musicapp.adapter.MusicViewPagerAdapter;
import com.example.musicapp.databinding.ActivityPlayMusicBinding;

public class PlayMusicActivity extends AppCompatActivity {
    private ActivityPlayMusicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayMusicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();
        initUI();

    }

    private void initUI() {
        MusicViewPagerAdapter musicViewPagerAdapter = new MusicViewPagerAdapter(this);
        binding.viewpager2.setAdapter(musicViewPagerAdapter);
        binding.indicator3.setViewPager(binding.viewpager2);
        binding.viewpager2.setCurrentItem(1);
    }

    private void initToolbar() {
        binding.toolbar.tittle.setText(R.string.music_player);
    }
}